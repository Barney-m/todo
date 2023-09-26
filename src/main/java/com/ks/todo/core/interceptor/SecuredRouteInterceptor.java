package com.ks.todo.core.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ks.todo.core.annotation.SecuredRoute;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Interceptor to validate user is authed
 * 
 * @author Ken Shen
 * @version 1.0
 */
@Component
public class SecuredRouteInterceptor implements HandlerInterceptor {

	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(HttpServletRequest,
	 *      HttpServletResponse, Object)
	 */
	// This method is executed before accessing the interface. We only need to write
	// the business logic to verify the login status here to verify the login status
	// before the user calls the specified interface.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			SecuredRoute authed = ((HandlerMethod) handler).getMethodAnnotation(SecuredRoute.class);

			//TODO: OAuth2 Validation

		}
		return true;
	}

	/**
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(HttpServletRequest,
	 *      HttpServletResponse, Object, ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
	}

	/**
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(HttpServletRequest,
	 *      HttpServletResponse, Object, Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
	}

}
