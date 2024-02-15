package com.aashdit.lms.web.controller;


import java.security.DigestException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.jboss.logging.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aashdit.lms.service.MailService;
import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.lms.utils.AesUtil;
import com.aashdit.lms.utils.ApplicationConstants;
import com.aashdit.lms.web.service.LoginService;
import com.aashdit.umt.dto.AuthRequest;
import com.aashdit.umt.model.LoggedInUser;
import com.aashdit.umt.model.Menu;
import com.aashdit.umt.model.Role;
import com.aashdit.umt.model.User;
import com.aashdit.umt.model.UserRoleMap;
import com.aashdit.umt.repository.MenuRepository;
import com.aashdit.umt.repository.UserRepository;
import com.aashdit.umt.service.RoleService;
import com.aashdit.umt.service.UserService;
import com.aashdit.umt.util.SecurityHelper;

@Controller
//@RequestMapping("/umt")
public class LoginController implements MessageSourceAware {
	private static final Logger logger = Logger.getLogger(LoginController.class);
	
	public void setMessageSource(MessageSource messageSource) {
	    this.messageSource = messageSource;
	  }
	private MessageSource messageSource;
	  @Autowired
	  private UserService userService;
	  
	  @Value("${post.login.url}")
	  private String POST_LOGIN_URL;
	  
	  @Value("${captcha.options}")
	  private String CAPTCHA_OPTIONS;
	
	  @Autowired
	  private MenuRepository menuRepository;
	  
	  @Autowired
	  private RoleService roleService;
	  
	  private List<Menu> m_menus;
	  
	  @Autowired
	  private UserRepository userRepository;
	  
	  @Autowired
	  private BCryptPasswordEncoder bCryptPasswordEncoder;
	  
	  @Autowired
	  private LoginService loginService;
	  
	  @Autowired
	  private MailService mailService;
	  
	  
	@PostMapping(path = {"/overwrite/umt/login"}, name = "Login Processing POST")
	  public String doLogin(HttpServletRequest request,HttpServletResponse httpServletResponse, AuthRequest authRequest, RedirectAttributes model) {
	    String returnPath = "redirect:" + this.POST_LOGIN_URL;
	    Boolean isOK = Boolean.valueOf(true);
	    String realPass = "";
	    
	   //authRequest.setUserName(getDecryptedUserName(request , authRequest.getUserName()));
	    try {
	      ServiceOutcome<User> svcOutcome = this.userService.findByUsername(authRequest.getUserName());
	      if (svcOutcome.getOutcome().booleanValue()) {
	        User user = (User)svcOutcome.getData();
	        if (user != null) {
	          if (user.getIsEnabled().booleanValue()) {
	            if (user.getIsLocked().booleanValue()) {
	              model.addFlashAttribute("err_msg", this.messageSource.getMessage("login.account.locked", null, LocaleContextHolder.getLocale()));
	              logger.debug("Account Locked");
	              isOK = Boolean.valueOf(false);
	              loginService.createFailedLoginHistory(user, request,httpServletResponse);
	            } 
	            if (user.getIsLoggedIn().booleanValue() && !user.getAllowMultipleSession().booleanValue()) {
	              model.addFlashAttribute("err_msg", this.messageSource.getMessage("login.account.loggedin", null, LocaleContextHolder.getLocale()));
	              logger.debug("Already Logged In");
	              isOK = Boolean.valueOf(false);
	              loginService.createFailedLoginHistory(user, request,httpServletResponse);
	            } 
	            if (isOK.booleanValue()) {
	              String svrCaptcha = (String)request.getSession().getAttribute("CAPTCHA_CODE");
	              Boolean verifiyCaptcha = Boolean.valueOf(true);
	              try {
	                if (this.CAPTCHA_OPTIONS.toLowerCase().contains("i"))
	                  verifiyCaptcha = Boolean.valueOf(false); 
	              } catch (Exception ex) {
	                logger.error("CAPTCHA_OPTIONS may not be defined ");
	                logger.error(ex.getMessage());
	              } 
	              if (verifiyCaptcha.booleanValue()) {
	                logger.debug("verifiying captcha");
	                logger.debug("User sent : " + authRequest.getCaptcha());
	                logger.debug("Server has : " + svrCaptcha);
	                if (!svrCaptcha.equals(authRequest.getCaptcha())) {
	                  model.addFlashAttribute("err_msg", this.messageSource.getMessage("login.account.badcaptcha", null, LocaleContextHolder.getLocale()));
	                  logger.debug("Captcha Mismatch");
	                  isOK = Boolean.valueOf(false);
	                  loginService.createFailedLoginHistory(user, request,httpServletResponse);
	                } 
	              } else {
	                logger.debug("ignoring captcha");
	              } 
	            } 
	            if (isOK.booleanValue()) {
	            	String decryptedPassword =  new String(java.util.Base64.getDecoder().decode(authRequest.getPassword()));
	            	String psk = request.getParameter("_csrf");
	    			psk = psk.substring(0, 16);
	    			AesUtil aesUtil = new AesUtil(128, 1000);
			        if (decryptedPassword != null && decryptedPassword.split("::").length == 3) {
			        	realPass = aesUtil.decrypt(decryptedPassword.split("::")[1], decryptedPassword.split("::")[0], psk, decryptedPassword.split("::")[2]);
			        }
	              BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	              String existingPassword = realPass;
	              String dbPassword = user.getPassword();
	              isOK = Boolean.valueOf(passwordEncoder.matches(existingPassword, dbPassword));
	              if (!isOK.booleanValue()) {
	                model.addFlashAttribute("err_msg", this.messageSource.getMessage("login.account.badcredentials", null, LocaleContextHolder.getLocale()));
	                logger.debug("Bad Credentials - Invalid password");
	                loginService.createFailedLoginHistory(user, request,httpServletResponse);
	              } 
	            } 
	            if (isOK.booleanValue()) {
	            	 /* Code for Session Hijacking */  
					HttpSession currentSession = request.getSession();
					currentSession.invalidate();

					HttpSession newSession = request.getSession(true);
					newSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
							SecurityContextHolder.getContext());

					String token = UUID.randomUUID().toString();
					newSession.setAttribute("tokenVal", token);
	            	
	              authorizeUser(request, user,httpServletResponse);

	            } else {
	              user.setWrongLoginCount(Integer.valueOf(((user.getWrongLoginCount() == null) ? 0 : user.getWrongLoginCount().intValue()) + 1));
	              if (user.getWrongLoginCount().intValue() >= 5) {
	                user.setIsLocked(Boolean.valueOf(true));
	                user.setIsLoggedIn(Boolean.valueOf(false));
	              } 
	              this.userService.save(user);
	            } 
	          } else {
	            model.addFlashAttribute("err_msg", this.messageSource.getMessage("login.account.disabled", null, LocaleContextHolder.getLocale()));
	            logger.debug("Account Disabled");
	            isOK = Boolean.valueOf(false);
	            loginService.createFailedLoginHistory(user, request,httpServletResponse);
	          } 
	        } else {
	          int count =  checkdDosAttack(request,authRequest.getUserName(),model,realPass);
	          
	          //model.addFlashAttribute("err_msg", this.messageSource.getMessage("login.account.badcredentials", null, LocaleContextHolder.getLocale()));
	          //logger.debug("Bad Credentials - No user found");
	          isOK = Boolean.valueOf(false);
	          
	          loginService.createNoUserLoginHistory(authRequest.getUserName(), request,httpServletResponse);
	        } 
	      } else {
	        model.addFlashAttribute("err_msg", svcOutcome.getMessage());
	        logger.debug("System Issue");
	        isOK = Boolean.valueOf(false);
	        loginService.createNoUserLoginHistory(authRequest.getUserName(), request,httpServletResponse);
	        return "redirect:/login";
	      } 
	    } catch (Exception ex) {
	      model.addFlashAttribute("err_msg", this.messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
	      logger.error(ex);
	      isOK = Boolean.valueOf(false);
	      return "redirect:/login";
	    } 
	    if (isOK.booleanValue())
	      return returnPath; 
	    return "redirect:/login";
	  }
	
	private String getDecryptedUserName(HttpServletRequest request, String userName) {
		String decryptedPassword =  new String(java.util.Base64.getDecoder().decode(userName));
    	String psk = request.getParameter("_csrf");
		psk = psk.substring(0, 16);
		AesUtil aesUtil = new AesUtil(128, 1000);
        if (decryptedPassword != null && decryptedPassword.split("::").length == 3) {
        	userName = aesUtil.decrypt(decryptedPassword.split("::")[1], decryptedPassword.split("::")[0], psk, decryptedPassword.split("::")[2]);
        }
		return userName;
	}

	public static byte[][] GenerateKeyAndIV(int keyLength, int ivLength, int iterations, byte[] salt, byte[] password, MessageDigest md) {

	    int digestLength = md.getDigestLength();
	    int requiredLength = (keyLength + ivLength + digestLength - 1) / digestLength * digestLength;
	    byte[] generatedData = new byte[requiredLength];
	    int generatedLength = 0;

	    try {
	        md.reset();

	        // Repeat process until sufficient data has been generated
	        while (generatedLength < keyLength + ivLength) {

	            // Digest data (last digest if available, password data, salt if available)
	            if (generatedLength > 0)
	                md.update(generatedData, generatedLength - digestLength, digestLength);
	            md.update(password);
	            if (salt != null)
	                md.update(salt, 0, 8);
	            md.digest(generatedData, generatedLength, digestLength);

	            // additional rounds
	            for (int i = 1; i < iterations; i++) {
	                md.update(generatedData, generatedLength, digestLength);
	                md.digest(generatedData, generatedLength, digestLength);
	            }

	            generatedLength += digestLength;
	        }

	        // Copy key and IV into separate byte arrays
	        byte[][] result = new byte[2][];
	        result[0] = Arrays.copyOfRange(generatedData, 0, keyLength);
	        if (ivLength > 0)
	            result[1] = Arrays.copyOfRange(generatedData, keyLength, keyLength + ivLength);

	        return result;

	    } catch (DigestException e) {
	        throw new RuntimeException(e);

	    } finally {
	        // Clean out temporary data
	        Arrays.fill(generatedData, (byte)0);
	    }
	}

	private void authorizeUser(HttpServletRequest request, User user,HttpServletResponse httpServletResponse) {
		    List<UserRoleMap> userRoleMaps = userService.findUserRoleMapByUserId(user.getUserId());
		    userRoleMaps = (List<UserRoleMap>)userRoleMaps.stream().filter(r -> r.getIsActive().booleanValue()).collect(Collectors.toList());
		    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		    List<Role> lstRoles = new ArrayList<>();
		    for (UserRoleMap urm : userRoleMaps) {
		      Role role = (Role)this.roleService.findByRoleId(urm.getRoleId()).getData();
		      grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleCode()));
		      if (role.getIsActive().booleanValue())
		        lstRoles.add(role); 
		    } 
		    user.setRoles(lstRoles);
		    LoggedInUser userDetails = new LoggedInUser(user.getUserName(), user.getPassword(), true, true, true, true, grantedAuthorities, user.getPrimaryRole(), user);
		    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, grantedAuthorities);
		    SecurityContext sc = SecurityContextHolder.getContext();
		    sc.setAuthentication((Authentication)usernamePasswordAuthenticationToken);
		    HttpSession session = request.getSession(true);
		    session.setAttribute("SPRING_SECURITY_CONTEXT", sc);
		    addSameSiteCookieAttribute(httpServletResponse); // add SameSite=strict cookie attribute
		    loadMenus(request, userDetails);
		    userService.createLoginHistory(user, request);
		  }
	 private void loadMenus(HttpServletRequest request, LoggedInUser liu) {
		    this.m_menus = this.menuRepository.findUIMenusByRole(liu.getPrimaryRole().getRoleId());
		    List<Menu> parents = (List<Menu>)this.m_menus.stream().filter(m -> (m.getParent() == null)).collect(Collectors.toList());
		    try {
		      parents = (List<Menu>)parents.stream().sorted((a, b) -> a.getDisplayOrder().intValue() - b.getDisplayOrder().intValue()).collect(Collectors.toList());
		    } catch (Exception ex) {
		      logger.error("ERROR SORTNG MENU -> " + ex.getMessage());
		    } 
		    List<Menu> finalList = new ArrayList<>();
		    parents.forEach(m -> {
		          m.setChildren(null);
		          List<Menu> children = getMenuChildren(m);
		          m.setChildren(children);
		          finalList.add(m);
		        });
		    request.getSession().setAttribute("USER_MENUS", finalList);
		    unLockUser(request.getParameter("userName"));
		  }
		  
		  private void unLockUser(String username) {
		    ServiceOutcome<User> svcOutcome = this.userService.findByUsername(username);
		    User user = (User)svcOutcome.getData();
		    if (user != null) {
		      user.setWrongLoginCount(Integer.valueOf(0));
		      user.setIsLocked(Boolean.valueOf(false));
		      user.setIsLoggedIn(Boolean.valueOf(true));
		      this.userService.save(user);
		    } 
		  }
		  
		  private List<Menu> getMenuChildren(Menu parent) {
			    List<Menu> children = (List<Menu>)this.m_menus.stream().filter(m -> (m.getParent() != null && m.getParent().getMenuId() == parent.getMenuId())).filter(m -> (m.getIsDisplay().booleanValue() == true)).collect(Collectors.toList());
			    try {
			      if (children != null) {
			        children = (List<Menu>)children.stream().sorted((a, b) -> a.getDisplayOrder().intValue() - b.getDisplayOrder().intValue()).collect(Collectors.toList());
			        children.forEach(m -> {
			              m.setChildren(null);
			              List<Menu> children2 = getMenuChildren(m);
			              m.setChildren(children2);
			            });
			      } 
			    } catch (Exception ex) {
			      logger.error("ERROR SORTNG CHILD MENU -> " + ex.getMessage());
			    } 
			    return children;
			  }
	
		  
		  private int checkdDosAttack(HttpServletRequest request,String username,RedirectAttributes attr,String password) {
				HttpSession session = request.getSession();
				int loginAttempt;
		        if (session.getAttribute("loginCount") == null){
		            session.setAttribute("loginCount", 0);
		            loginAttempt = 0;
		        }else{
		             loginAttempt = (Integer) session.getAttribute("loginCount");
		        }
		        
		      //this is 3 attempt counting from 0,1,2
		        if (loginAttempt >= 5){        
		            long lastAccessedTime = session.getLastAccessedTime();
		            Date date = new Date();
		            long currentTime = date.getTime();
		            long timeDiff = currentTime - lastAccessedTime;
		            // 20 minutes in milliseconds  
		            if (timeDiff >= 600000){
		                //invalidate user session, so they can try again
		                session.invalidate();
		            }
		            else{
		                 // Error message 
		            	 loginAttempt++;
		            	 attr.addFlashAttribute("err_msg","You have exceeded the 3 failed login attempt. Please contact your system admin for correct username and password.");
		            	 //loginService.createNoUserLoginHistory(username, request);
		            }  

		        }else{
		        	 //userService.saveFailedLoginHistory(request, username,password);
		             loginAttempt++;
		             int allowLogin = 5-loginAttempt;
		             attr.addFlashAttribute("err_msg","loginAttempt= "+loginAttempt+". Invalid username or password. You have "+allowLogin+" attempts remaining. Please try again!");
		             //loginService.createNoUserLoginHistory(username, request);
		        }
		        session.setAttribute("loginCount",loginAttempt);
		        session.setAttribute("lastAccessTime",session.getLastAccessedTime());
		        return loginAttempt;
			}
		  
		  private void addSameSiteCookieAttribute(HttpServletResponse response) {
		        Collection<String> headers = response.getHeaders(HttpHeaders.SET_COOKIE);
		        boolean firstHeader = true;
		        for (String header : headers) { // there can be multiple Set-Cookie attributes
		            if (firstHeader) {
		                response.setHeader(HttpHeaders.SET_COOKIE, String.format("%s; %s", header, "SameSite=Strict"));
		                firstHeader = false;
		                continue;
		            }
		            response.addHeader(HttpHeaders.SET_COOKIE, String.format("%s; %s", header, "SameSite=Strict"));
		        }
		    }
		  
		  @Secured({ ApplicationConstants.ROLE_ADMIN})	  
			@PostMapping(value="/umt/user/reset/password")	
			public String resetPassword(RedirectAttributes attr,Long userId) {
				try {
					User userDtls = userRepository.findById(userId).get();
					userDtls.setPassword(bCryptPasswordEncoder.encode("123456"));
					userDtls.setIsActive(true);
					userDtls.setIsLocked(false);
					userDtls.setIsEnabled(true);
					userRepository.save(userDtls);
					attr.addFlashAttribute("success_msg", "Password successfully reset");
				} catch (Exception e) {
					logger.error("ERROR IN RESET PASSWORD -> " + e.getMessage());
					attr.addFlashAttribute("error_msg", "Something Went Wrong !!");
				}
				
				return "redirect:/umt/user/list";
				
			}
			@Secured({ApplicationConstants.ROLE_SHOP,ApplicationConstants.ROLE_BANK})	  
			@GetMapping(value="/umt/user/changepassword")	
			public String changePassword(Model model,Long userId) {
				try {
					User user=SecurityHelper.getCurrentUser();
					ServiceOutcome<User> outcome=userService.findByUserId(user.getUserId());
					model.addAttribute("userDetails", outcome.getData());
				} catch (Exception e) {
					logger.error(e);
				}
				
				
				return "site.changePassword";
				
			}
			
			
			@GetMapping(value="/public/termsConditions")	
			public String termsAndConditions(Model model,Long userId) {
				
				
				return "site.termsConditions";
				
			}
			@GetMapping(value="/umt/user/checkCurrentPassword")
			@ResponseBody
			public String checkCurrentPassword(Model model,String pass) {
				JSONObject jsonObject=new JSONObject();
				try {
					User user = SecurityHelper.getCurrentUser();
					BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
					if(passwordEncoder.matches(pass,user.getPassword())) {
						jsonObject.put("validated", true);
					}else {
						jsonObject.put("validated", false);
					}
					
				} catch (Exception e) {
					jsonObject.put("validated", false);
					logger.error(e);
				}
				
				return jsonObject.toString();
				
			}
			
			@GetMapping(value="/public/forgot/password")	
			public String forgotPassword(Model model,Long userId) {
				
				
				return "site.forgotPassword";
				
			}
					
			@PostMapping(value="/public/forgot/password/filter")	
			public String passwordFilter(RedirectAttributes attr,String userName,String emailId) {
				try {
					ServiceOutcome<User> svcOutcome = this.userService.findByUsername(userName);
				      if (svcOutcome.getOutcome() && Optional.ofNullable(svcOutcome.getData()).isPresent()) {
				    	 if(svcOutcome.getData().getEmail().equals(emailId)) {
				    		    String password = RandomStringUtils.randomAlphabetic(5);
								Boolean outcome = mailService.mailLoginCredentials(password, "LOGIN CREDENTIALS", svcOutcome.getData());
								if(outcome) {
									User userDtls = userRepository.findById(svcOutcome.getData().getUserId()).get();
									userDtls.setPassword(bCryptPasswordEncoder.encode(password));
									userRepository.save(userDtls);
									attr.addFlashAttribute("success_msg", "Password reset request was sent successfully.Please check your mail to reset your password."); 
								}else {
									attr.addFlashAttribute("error_msg", "Something went wrong !!");  
								}
				    	 
				    	 }else {
				    		 attr.addFlashAttribute("error_msg", "Invalid User Name or Email Id.Please Contect Admin");  
				    	 }
				      }else {
				    	  attr.addFlashAttribute("error_msg", "Invalid User Name or Email Id.Please Contect Admin ");  
				      }
					
				} catch (Exception e) {
					logger.error(e);
				}
				
				return "redirect:/public/forgot/password";
				
			}
}
