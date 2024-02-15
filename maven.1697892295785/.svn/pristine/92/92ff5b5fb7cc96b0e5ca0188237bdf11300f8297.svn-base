package com.aashdit.lms.web.controller;

import java.security.Principal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.lms.model.Category;
import com.aashdit.lms.service.CommonService;
import com.aashdit.lms.service.MasterService;
import com.aashdit.lms.service.MemberService;
import com.aashdit.lms.utils.ApplicationConstants;
import com.aashdit.umt.model.Role;
import com.aashdit.umt.model.User;
import com.aashdit.umt.model.UserLoginHistory;
import com.aashdit.umt.repository.UserLoginHistoryRepository;
import com.aashdit.umt.util.SecurityHelper;


@Controller
public class HomeController implements MessageSourceAware {

	final static Logger logger = Logger.getLogger(HomeController.class);
	
	private MessageSource messageSource;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private MasterService masterService;
   
    @Autowired
    private UserLoginHistoryRepository userLoginHistoryRepository;
    

	@GetMapping(path = "/home", name = "User Home Page")
	public String home(Model model,HttpSession session) {
		String role = "";
		User user = SecurityHelper.getCurrentUser();
		try {
			if (user != null) {
				Role userRole = user.getPrimaryRole();
				role = userRole.getRoleCode().toLowerCase().replace("role_", "");
				ServiceOutcome<List<Category>> categoryList = masterService.getCategoryList(true);
				model.addAttribute("categoryList", categoryList.getData());

				model.addAttribute("languageList", commonService.getAllData("LANGUAGE").getData());
			}
		} catch (Exception ex) {
			logger.error(ex);
		}
		return "site." + role + ".home";
	}

	/**
	 * return to base path
	 * 
	 * @param principal
	 */
	@GetMapping(path = "/")
	public String index(Principal principal, HttpServletRequest request, RedirectAttributes attr) {
		if (principal != null) {
			return "redirect:/home";
		} else {
			return "redirect:/login";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/navbarActive", method = RequestMethod.GET)
	public String ActiveNavbar(@RequestParam("data") long id, HttpSession session) throws Exception {
		try {
			if (id == 1) {
				session.setAttribute("flag", "activenav");
			} else {
				session.setAttribute("flag", "deactivenav");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return "true";
	}
	
	/*@GetMapping(value="/public/requesetForOTP")
	public @ResponseBody String requesetForOTP(String mobileNo,String otpType) {
		JSONObject jsonObject=new JSONObject();
		try {
			User user = userRepository.findByUserName(mobileNo);
			if(user!=null) {
				if(otpType.equals("CREATE")) {
					user.setPassword(this.bCryptPasswordEncoder.encode("456789"));
					userRepository.save(user);
				}else {
					user.setPassword(this.bCryptPasswordEncoder.encode("123456"));
					userRepository.save(user);
				}
				
				jsonObject.put("mobileNoIsAvail", true);
			}else {
				jsonObject.put("mobileNoIsAvail", false);
			}
		
		} catch (Exception e) {
			logger.error(e);
		}
		return jsonObject.toString();
		
	}
	
	@GetMapping(value="/public/verifyOTPWithMobileNo")
	public @ResponseBody String verifyOTPWithMobileNo(String mobileNo,String mobileOtp) {
		 Boolean isOK = Boolean.valueOf(false);
			JSONObject jsonObject=new JSONObject();
		try {
			User user = userRepository.findByUserName(mobileNo);
			
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        String existingPassword = mobileOtp;
	        String dbPassword = user.getPassword();
	        isOK = Boolean.valueOf(passwordEncoder.matches(existingPassword, dbPassword));
	        if (isOK.booleanValue()) {
	          jsonObject.put("verifyOTP", isOK);
	        } else {
	          jsonObject.put("verifyOTP", isOK);
	        }
		} catch (Exception e) {
			logger.error(e);
			 jsonObject.put("verifyOTP", isOK);
		}
		
		return jsonObject.toString();
		
	}*/

	
	/* @PostMapping(path = {"/public/umt/login"}, name = "Login Processing POST")
	public String publicDoLogin(HttpServletRequest request, @ModelAttribute AuthRequest authRequest, RedirectAttributes model) {
		
		 Boolean isOK = Boolean.valueOf(true);
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
		            } 
		            if (user.getIsLoggedIn().booleanValue() && !user.getAllowMultipleSession().booleanValue()) {
		              model.addFlashAttribute("err_msg", this.messageSource.getMessage("login.account.loggedin", null, LocaleContextHolder.getLocale()));
		              logger.debug("Already Logged In");
		              isOK = Boolean.valueOf(false);
		            } 
		            
		            if (isOK.booleanValue()) {
		              BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		              String existingPassword = authRequest.getPassword();
		              String dbPassword = user.getPassword();
		              isOK = Boolean.valueOf(passwordEncoder.matches(existingPassword, dbPassword));
		              if (!isOK.booleanValue()) {
		                model.addFlashAttribute("err_msg", this.messageSource.getMessage("login.account.badcredentials", null, LocaleContextHolder.getLocale()));
		                logger.debug("Bad Credentials - Invalid password");
		              } 
		            } 
		            if (isOK.booleanValue()) {
		            	authorizeUser(request, user, authRequest.getAppCode());
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
		          } 
		        } else {
		          model.addFlashAttribute("err_msg", this.messageSource.getMessage("login.account.badcredentials", null, LocaleContextHolder.getLocale()));
		          logger.debug("Bad Credentials - No user found");
		          isOK = Boolean.valueOf(false);
		        } 
		      } else {
		        model.addFlashAttribute("err_msg", svcOutcome.getMessage());
		        logger.debug("System Issue");
		        isOK = Boolean.valueOf(false);
		      } 
		    } catch (Exception ex) {
		      model.addFlashAttribute("err_msg", this.messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		      logger.error(ex);
		      isOK = Boolean.valueOf(false);
		    } 
		    if (isOK.booleanValue()) {
		    	return "redirect:/tenant/getPayment/list";
		    }else {
		       return "redirect:/login";
		    }
			
		   
		   
	}

	private void authorizeUser(HttpServletRequest request, User user, String appCode) {
		 List<UserRoleMap> userRoleMaps = this.userService.findUserRoleMapByUserId(user.getUserId());
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
		   // loadMenus(request, userDetails, appCode);
		    this.userService.createLoginHistory(user, request);
		
	} */

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource =messageSource;
		
	}
	
	@GetMapping(value="/admin/user/login/history")
	public String userLoginHistory(Model model) {
		
		try {
			
			List<UserLoginHistory> userHistories=userLoginHistoryRepository.findAll();
			userHistories.sort(Comparator.comparing(UserLoginHistory::getLoggedInDate));
			Collections.reverse(userHistories);
			model.addAttribute("userHistoryList", userHistories);
			
		} catch (Exception e) {
			logger.error("Exception occured in getting login history in HomeController-->"+e);
		}
		
		return "site.admin.loginHistory";
		
	}
}
