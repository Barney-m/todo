package com.ks.todo.core.svc;

import org.springframework.security.core.Authentication;

public interface HttpSecuritySvc {

	/**
	 * Get Authentication from SecurityContextHolder
	 * 
	 * @return Authentication
	 */
	Authentication getAuthentication();
	
	/**
	 * Get Username from SecurityContextHolder
	 * 
	 * @return String
	 */
	String getUsername();
}
