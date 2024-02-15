package com.aashdit.lms.web.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionEvent;

import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.umt.model.User;

public interface LoginService {

	ServiceOutcome<Boolean> createFailedLoginHistory(User user, HttpServletRequest request, HttpServletResponse httpServletResponse);

	ServiceOutcome<Boolean> createNoUserLoginHistory(String userName, HttpServletRequest request, HttpServletResponse httpServletResponse);


}
