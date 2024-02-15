package com.aashdit.lms.web.controller;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.lms.utils.ApplicationConstants;
import com.aashdit.lms.web.security.JwtUtil;
import com.aashdit.umt.dto.AuthRequest;
import com.aashdit.umt.model.User;
import com.aashdit.umt.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = "/api")
public class ApiPublicController {


    final Logger logger = Logger.getLogger(this.getClass());
    List<String> allowedMobileUser = Arrays.asList("ROLE_MEMBER",ApplicationConstants.ROLE_PUBLIC);

//    @Value("${UPLOAD.FILE.USER.MANUAL.PATH}")
//    private String FILE_PATH;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;
    

    @PostMapping(path="/login",produces =MediaType.APPLICATION_JSON_VALUE)
    public ServiceOutcome<String> userLogin(@RequestBody AuthRequest authRequest,HttpServletRequest request) throws Exception {

        ServiceOutcome<String> response = new ServiceOutcome<>();
        try {
        	try {
        		   authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
			} catch (Exception e) {
				 User user = userService.findByUsername(authRequest.getUserName()).getData();
				 if(Optional.ofNullable(user).isPresent()) 
    	          user.setWrongLoginCount(Integer.valueOf(((user.getWrongLoginCount() == null) ? 0 : user.getWrongLoginCount().intValue()) + 1));
  	              if (user.getWrongLoginCount().intValue() >= 200) {
  	                user.setIsLocked(Boolean.valueOf(true));
  	                user.setIsLoggedIn(Boolean.valueOf(false));
  	              response.setOutcome(false);
  	              response.setMessage("Account Locked.");
  	              } else{
  	            	 response.setOutcome(false);
  	                 response.setMessage("Invalid username or password.");
  	              }
  	            this.userService.save(user);
  	         
              logger.error(e);
              return response;
			}
				 ServiceOutcome<User> svcOutcome = userService.findByUsername(authRequest.getUserName());
		            if (svcOutcome.getOutcome()) {
		                User user = svcOutcome.getData();
		                if (user != null) {
		                    if (user.getIsEnabled()) {
		                        Boolean isOK = true;
		                        if (user.getIsLocked())
		                        {
		                            response.setOutcome(false);
		                            response.setMessage("Sorry. Your account has been locked. Please contact the System Administrator");
		                            logger.debug(response.getMessage());
		                            isOK = false;
		                        }

		                        if (user.getIsLoggedIn() && !user.getAllowMultipleSession())
		                        {
		                            response.setOutcome(false);
		                            response.setMessage("Sorry. You are already logged in.");
		                            logger.debug(response.getMessage());
		                            isOK = false;
		                        }

		                        if (isOK)
		                        {
		                            if (!allowedMobileUser.stream().anyMatch(f -> f.equals(user.getPrimaryRole().getRoleCode())))
		                            {
		                                response.setOutcome(false);
		                                response.setMessage("This role is blocked for mobile activity");
		                                response.setData("==gyame==");
		                                logger.debug("This role is blocked for mobile activity");
		                            }
		                            else
		                            {
		                                response.setData(jwtUtil.generateToken(authRequest.getUserName()));
		                                unLockUser(authRequest.getUserName());
		                            }
		                        }

		                    } else {
		                        response.setOutcome(false);
		                        response.setMessage("Sorry, this account has been deactivated.");
		                        logger.debug(response.getMessage());
		                    }
		                } else {
		                    response.setOutcome(false);
		                    response.setMessage("Invalid username or password.");
		                    logger.debug(response.getMessage());
		                }
		            }
		            else
		            {
		                response.setOutcome(false);
		                response.setMessage("Request Failed Due to System Issue");
		                logger.debug(response);
		            }
			

           
        } catch (Exception ex) {
            response.setOutcome(false);
            response.setMessage("Invalid username or password.");
            logger.debug(ex);
        }

        return response;
    }
	
    private void unLockUser(String username) {
	    ServiceOutcome<User> svcOutcome = this.userService.findByUsername(username);
	    User user = (User)svcOutcome.getData();
	    if (user != null) {
	      user.setWrongLoginCount(Integer.valueOf(0));
	      user.setIsLocked(Boolean.valueOf(false));
	     // user.setIsLoggedIn(Boolean.valueOf(true));
	      this.userService.save(user);
	    } 
	  }
}