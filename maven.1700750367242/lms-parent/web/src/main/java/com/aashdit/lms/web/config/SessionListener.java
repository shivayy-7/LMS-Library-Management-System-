package com.aashdit.lms.web.config;

import java.util.ResourceBundle;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.aashdit.lms.utils.ApplicationContentUtils1;
import com.aashdit.lms.web.service.LoginService;
//import com.aashdit.umt.service.UMTLoginService;
import com.aashdit.umt.service.UserService;
//import com.aashdit.umt.util.ApplicationContentUtils;

@Configuration
public class SessionListener implements HttpSessionListener  {

	private final static Logger logger = Logger.getLogger(SessionListener.class.getName()); 
	
	 @Autowired
     private ApplicationContentUtils1 applicationContentUtils;
	
	
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        logger.info("Session Created");
        ResourceBundle rb = ResourceBundle.getBundle("application");
        String sessionTimeoutPeriod = rb.getString("session.timeout.period").trim(); 
        event.getSession().setMaxInactiveInterval(Integer.parseInt(sessionTimeoutPeriod)); 
        applicationContentUtils.getDynamicWebpageParameters(event);
    }

	@Override
    public void sessionDestroyed(HttpSessionEvent event) {
       logger.info("Session Expired");
    }
	
	
    
}
	