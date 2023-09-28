package com.ks.todo.core.svc.impl;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Service;

import com.ks.todo.core.svc.HttpSecuritySvc;

@Service
public class HttpSecuritySvcImpl implements HttpSecuritySvc {

	/**
	 * @see com.ks.todo.core.svc.HttpSecuritySvc#getAuthentication()
	 */
	@Override
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	/**
	 * @see com.ks.todo.core.svc.HttpSecuritySvc#getUsername()
	 */
	@Override
	public String getUsername() {
		if (null != getAuthentication()) {
			Object principal = getAuthentication().getPrincipal();
			
			if (principal instanceof DefaultOAuth2User) {
				return ((DefaultOAuth2User) principal).getAttribute("login");
			} else if (principal instanceof Map) {
				return ((Map) principal).get("login").toString();
			}
		}
	
		return null;
	}

}
