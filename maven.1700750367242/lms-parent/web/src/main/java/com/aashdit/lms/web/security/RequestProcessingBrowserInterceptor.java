package com.aashdit.lms.web.security;

import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.umt.model.LoggedInUser;
import com.aashdit.umt.model.User;
import com.aashdit.umt.service.UserService;

public class RequestProcessingBrowserInterceptor extends HandlerInterceptorAdapter {

	private final Logger logger = Logger.getLogger(this.getClass());
	static ResourceBundle applRB = ResourceBundle.getBundle("application");
	private static final String CONTEXT_PATH = applRB.getString("server.contextPath");

	@Autowired
	private UserService userService;

	private void updateOrSetATCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		Boolean hasATCookie = false;

		if (cookies != null && cookies.length > 0) {
			for (int idx = 0; idx < cookies.length; idx++) {
				Cookie ck = cookies[idx];
				if (ck.getName().equals("ANTI_TAMPER_KEY")) {

					HttpSession session = request.getSession();
					session.setAttribute("ANTI_TAMPER_KEY", ck.getValue());

					ck.setValue(ck.getValue());
					// ck.setHttpOnly(true);
					response.addCookie(ck);

					hasATCookie = true;
				}
			}
		}

		if (hasATCookie == false) {
			String antiTamperKey = UUID.randomUUID().toString();
			HttpSession session = request.getSession();
			session.setAttribute("ANTI_TAMPER_KEY", antiTamperKey);

			Cookie dummyCookie = new Cookie("ANTI_TAMPER_KEY", antiTamperKey);
			response.addCookie(dummyCookie);
		}
	}

	/*
	 * @Override public void postHandle(HttpServletRequest request,
	 * HttpServletResponse response, Object handler, ModelAndView modelAndView)
	 * throws Exception {
	 * 
	 * if (request.getRequestURI().contains(".") &&
	 * request.getRequestURI().contains(".htm")) {
	 * 
	 * updateOrSetATCookie(request, response); }
	 * 
	 * if (!request.getRequestURI().contains(".")) {
	 * 
	 * updateOrSetATCookie(request, response); }
	 * 
	 * }
	 */

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		boolean outcome = true;

		if (request.getMethod().equalsIgnoreCase("POST")) {

			/*
			 * if (request.getHeader("Origin") == null) { return false; } else { String
			 * referrer = request.getHeader("Referer"); if
			 * (!referrer.startsWith(request.getHeader("Origin"))) { return false; } }
			 */

			return true;
		}

		/*
		if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
			if (!request.getRequestURI().contains("antitamperprotection")) {
				return true;
			}
		}

		if (request.getRequestURI().contains(".") && request.getRequestURI().contains(".htm")) {

			outcome = checkForTampering(request);
		}

		if (!request.getRequestURI().contains(".")) {
			outcome = checkForTampering(request);
		}

		if (request.getRequestURI().contains("antitamperprotection")) {
			outcome = checkForTampering(request);
		}

		if (!outcome) {
			response.sendRedirect(CONTEXT_PATH);
			return false;
		}
		*/
		// response.sendRedirect("login");

		return super.preHandle(request, response, handler);
	}

	private boolean checkForTampering(HttpServletRequest request) {
		boolean outcome = true;

		Cookie[] cookies = request.getCookies();

		// Exclude the Login URL
		if (request.getRequestURI().contains("login")) {
			return true;
		}

		if (request.getRequestURI().contains("assets")) {
			return true;
		}

		if (request.getRequestURI().contains("logoutForm")) {
			return true;
		}

		// Not Authenticated
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			return true;
		}

		// Anonymous User
		if (auth != null && !(auth.getPrincipal() instanceof LoggedInUser)) {

			return true;
		}

		String serverAntiTamperKey = (String) request.getSession().getAttribute("ANTI_TAMPER_KEY");
		Boolean keyMatch = false;

		if (cookies != null && cookies.length > 0) {
			for (int idx = 0; idx < cookies.length; idx++) {
				Cookie ck = cookies[idx];
				if (ck.getName().equals("ANTI_TAMPER_KEY")) {
					String clientAntiTamperKey = ck.getValue();

					if (serverAntiTamperKey.equals(clientAntiTamperKey)) {
						keyMatch = true;
						break;
					}

					System.out.println("clientAntiTamperKey is ===========" + clientAntiTamperKey);
					System.out.println("serverAntiTamperKey is ===========" + serverAntiTamperKey);
				}
			}

			if (!keyMatch) {
				LoggedInUser ud;

				if (auth != null && auth.getPrincipal() instanceof LoggedInUser) {
					ud = (LoggedInUser) auth.getPrincipal();

					if (ud != null) {
						ServiceOutcome<User> svcOutcome = userService.findByUsername(ud.getDbUser().getUserName());
						User user = svcOutcome.getData();
						if (svcOutcome.getData() != null) {
							user.setIsLoggedIn(false);
							userService.save(user);
						}
						request.getSession().invalidate();
						SecurityContextHolder.getContext().setAuthentication(null);
						request.getSession().setAttribute(
								HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
								SecurityContextHolder.getContext());
						outcome = false;
					} else {
						outcome = true;
					}
				}
			}

		}

		/* Update when the user last requested a URL */
		/* This is used to indicate a User session is no longer active */
		if (auth != null && (auth.getPrincipal() instanceof LoggedInUser)) {

			LoggedInUser ud = (LoggedInUser) auth.getPrincipal();

			if (ud != null) {
				ServiceOutcome<User> svcOutcome = userService.findByUsername(ud.getDbUser().getUserName());
				User user = svcOutcome.getData();
				if (user != null) {
					user.setLastRequestTime(new Date());
					userService.save(user);
				}
			}
		}

		return outcome;
	}

}