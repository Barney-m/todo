package com.ks.todo.auth;

import java.io.IOException;
import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuth2GitHubAuthenticationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String xAuth = request.getHeader("Authorization");// here is your token value
		// if Authentication Header not starts with Bearer
		if (null != xAuth && xAuth.startsWith("Bearer ") && SecurityContextHolder.getContext().getAuthentication() == null) {
			String token = xAuth.substring(7, xAuth.length());
			
			RestTemplate restTemplate = initRestTemplate(token);
			@SuppressWarnings("unchecked")
			Map<String, Object> userObj = (Map<String, Object>) restTemplate.getForObject("https://api.github.com/user", Object.class);

			if (null != userObj) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userObj,
						null, null);

				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		
		filterChain.doFilter(request, response);
	}
	
	private RestTemplate initRestTemplate(String token) {
		return new RestTemplateBuilder().defaultHeader("Authorization", "Bearer " + token).build();
	}
}
