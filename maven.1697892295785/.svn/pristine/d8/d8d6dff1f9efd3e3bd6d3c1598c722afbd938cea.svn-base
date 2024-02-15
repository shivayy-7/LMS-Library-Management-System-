package com.aashdit.lms.web.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionEvent;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.framework.core.util.ClientInfo;
import com.aashdit.umt.model.LoggedInUser;
import com.aashdit.umt.model.Role;
import com.aashdit.umt.model.User;
import com.aashdit.umt.model.UserLoginHistory;
import com.aashdit.umt.model.UserRoleMap;
import com.aashdit.umt.repository.UserLoginHistoryRepository;
import com.aashdit.umt.repository.UserRepository;
import com.aashdit.umt.service.RoleService;
import com.aashdit.umt.service.UserService;
import com.aashdit.umt.util.SecurityHelper;

@Service
public class LoginServiceImpl implements LoginService {
	
	final static Logger logger = Logger.getLogger(LoginServiceImpl.class);
	
	@Autowired
	private UserLoginHistoryRepository ulHistoryReporsitory;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;

	@Override
	public ServiceOutcome<Boolean> createFailedLoginHistory(User user, HttpServletRequest request,HttpServletResponse response) {
		ServiceOutcome<Boolean> svcOutcome = new ServiceOutcome<>();
		try
		{
			Boolean result = false;
			UserLoginHistory ulHistory = new UserLoginHistory();
		
			ulHistory.setBrowserDetails(ClientInfo.getClientBrowser(request));
			ulHistory.setEmail(user.getEmail());
			ulHistory.setFirstName(user.getFirstName());
			ulHistory.setLastName(user.getLastName());
			ulHistory.setLoggedInDate(new Date());
			ulHistory.setLoggedOutDate(null);
			ulHistory.setLoginStatus("LOGIN");
			ulHistory.setLoginType(user.getPrimaryRole().getRoleCode());
			ulHistory.setMobile(user.getMobile());
			ulHistory.setOsDetails(ClientInfo.getClientOS(request));
			ulHistory.setUserName(user.getUserName());
			ulHistory.setUserId(user.getUserId());
			ulHistory.setIpAddress(ClientInfo.getClientIpAddr(request));
			
			if(SecurityHelper.getCurrentUser()== null) {
				ServiceOutcome<User> user1 = userService.findByUsername("system");
				authorizedUser(user1.getData());
				result=true;
			}
			
			ulHistoryReporsitory.save(ulHistory);
			
			if(result) {
				Cookie[] cookies = request.getCookies();
				String[] cookieNames = new String[cookies.length];

				for(int i = 0; i < cookies.length; i++)
				{
					cookieNames[i] = cookies[i].getName();
				}
				 
		
				CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler(cookieNames);
			    SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
			    cookieClearingLogoutHandler.logout(request, response, null);
			    securityContextLogoutHandler.logout(request, response, null);
			}
			
			svcOutcome.setData(true);
		}
		catch(Exception ex)
		{
			svcOutcome.setOutcome(false);
			svcOutcome.setData(false);
			svcOutcome.setMessage(ex.getMessage());
			logger.error("Exception occured in createFailedLoginHistory in LoginServiceImpl-->",ex);
		}
		
		return svcOutcome;
	}

	@Override
	public ServiceOutcome<Boolean> createNoUserLoginHistory(String userName, HttpServletRequest request,HttpServletResponse response) {
		ServiceOutcome<Boolean> svcOutcome = new ServiceOutcome<>();
		try
		{
			ServiceOutcome<User> user = userService.findByUsername("system");

			UserLoginHistory ulHistory = new UserLoginHistory();
		
			ulHistory.setBrowserDetails(ClientInfo.getClientBrowser(request));
			ulHistory.setEmail("failedLogin@ormas.in");
			ulHistory.setFirstName("Failed");
			ulHistory.setLastName("Login");
			ulHistory.setLoggedInDate(new Date());
			ulHistory.setLoggedOutDate(null);
			ulHistory.setLoginStatus("FAILED LOGIN");
			ulHistory.setLoginType("NO ROLE");
			ulHistory.setMobile("9876543210");
			ulHistory.setOsDetails(ClientInfo.getClientOS(request));
			ulHistory.setUserName(userName);
			ulHistory.setIpAddress(ClientInfo.getClientIpAddr(request));
			
			authorizedUser(user.getData());
			ulHistoryReporsitory.save(ulHistory);
			
			Cookie[] cookies = request.getCookies();
			String[] cookieNames = new String[cookies.length];

			for(int i = 0; i < cookies.length; i++)
			{
				cookieNames[i] = cookies[i].getName();
			}
			 
	
			CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler(cookieNames);
		    SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
		    cookieClearingLogoutHandler.logout(request, response, null);
		    securityContextLogoutHandler.logout(request, response, null);
			svcOutcome.setData(true);
		}
		catch(Exception ex)
		{
			svcOutcome.setOutcome(false);
			svcOutcome.setData(false);
			svcOutcome.setMessage(ex.getMessage());
			logger.error("Exception occured in createNoUserLoginHistory in LoginServiceImpl-->",ex);
		}
		
		return svcOutcome;
	}

	public void authorizedUser(User user) {
	    List<UserRoleMap> userRoleMaps = this.userService.findUserRoleMapByUserId(user.getUserId());
	    userRoleMaps = (List<UserRoleMap>)userRoleMaps.stream().filter(r -> r.getIsActive().booleanValue()).collect(Collectors.toList());
	    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
	    List<Role> lstRoles = new ArrayList<>();
	    for (UserRoleMap urm : userRoleMaps) {
	      Role role = roleService.findByRoleId(urm.getRoleId()).getData();
	      grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleCode()));
	      if (role.getIsActive().booleanValue())
	        lstRoles.add(role); 
	    } 
	    user.setRoles(lstRoles);
	    LoggedInUser userDetails = new LoggedInUser(user.getUserName(), user.getPassword(), true, true, true, true, grantedAuthorities, user.getPrimaryRole(), user);
	    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, grantedAuthorities);
	    SecurityContext sc = SecurityContextHolder.getContext();
	    sc.setAuthentication(usernamePasswordAuthenticationToken);
	  }

	
}