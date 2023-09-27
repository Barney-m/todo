package com.ks.todo.core.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.lang.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ks.todo.auth.repo.UserAttributeRepo;
import com.ks.todo.core.annotation.SecuredRoute;
import com.ks.todo.core.exception.SvcException;

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
	protected UserAttributeRepo userAttributeRepo;

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

			// Not Annotated
			if (null == authed) {
				authed = ((HandlerMethod) handler).getMethod().getDeclaringClass().getAnnotation(SecuredRoute.class);
			}

			// Annotated
			if (null != authed) {
				
				if (null == SecurityContextHolder.getContext().getAuthentication() || !SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
					throw new SvcException("Not Authorized!");
				}
				
			}

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

	private RestTemplate initRestTemplate(String token) {
		return new RestTemplateBuilder().defaultHeader("Authorization", "Bearer " + token).build();
	}
}
