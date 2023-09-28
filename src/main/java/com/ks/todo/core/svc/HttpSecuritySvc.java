package com.ks.todo.core.svc;

import org.springframework.security.core.Authentication;

public interface HttpSecuritySvc {

	Authentication getAuthentication();
	
	String getUsername();
}
