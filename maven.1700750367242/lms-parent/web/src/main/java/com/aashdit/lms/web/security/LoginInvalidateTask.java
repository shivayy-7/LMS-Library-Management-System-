package com.aashdit.lms.web.security;

import java.util.Date;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.aashdit.umt.model.User;
import com.aashdit.umt.repository.UserRepository;
import com.aashdit.umt.service.UserService;


@Component(value = "loginInvalidateTask")
public class LoginInvalidateTask {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;

	@Scheduled(fixedRate = 300000) // Every 5 Mins
	private void resetExpiredLoginFlags() {
		try {
			Date currentDate = new Date();
		//	logger.debug("STARTED TASK resetExpiredLoginFlags ->" + currentDate.toLocaleString());
			List<User> lstUsers = userRepository.getLoggedInUsers();
			if (lstUsers != null) {
				for (User user : lstUsers) {
					if (user.getLastRequestTime() != null) {
						// In seconds becuase we dive by 1000
						long difference = (currentDate.getTime() - user.getLastRequestTime().getTime()) / 1000;
						if (Math.abs(difference) > 20 * 60) {
							// Last request was 20 minutes ago and user is marked as Logged in
							//userService.closeLoginHistory(user);
							user.setIsLocked(user.getIsLocked() == true ? false : user.getIsLocked());
							user.setIsLoggedIn(false);
							userRepository.save(user);
						}

					} else {
						user.setIsLoggedIn(false);
						userRepository.save(user);
					}
				}
			}
		} catch (Exception ex) {
			logger.error("ERROR IN TASK resetExpiredLoginFlags -> " + ex.getMessage());
			logger.error(ex.getStackTrace());
		}
	}
}
