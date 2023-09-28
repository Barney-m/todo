package com.ks.todo.auth.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ks.todo.auth.bean.User;
import com.ks.todo.auth.bean.UserAttribute;
import com.ks.todo.auth.constant.AuthConstant;
import com.ks.todo.auth.repo.UserAttributeRepo;
import com.ks.todo.auth.repo.UserRepo;
import com.ks.todo.core.ClassObjFactory;
import com.ks.todo.core.exception.SvcException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;

@Transactional
@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Authentication APIs")
public class AuthRest {

	@Autowired
	protected OAuth2AuthorizedClientService clientService;

	@Autowired
	protected UserRepo userRepo;

	@Autowired
	protected UserAttributeRepo userAttributeRepo;

	@Operation(summary = "Login Success", description = "Login Success")
	@GetMapping("/login/success")
	public String loginSuccess(Authentication authentication) {
		OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;

		OAuth2AuthorizedClient client = clientService
				.loadAuthorizedClient(oauthToken.getAuthorizedClientRegistrationId(), oauthToken.getName());

		saveOAuth2User(client, oauthToken);
		return client.getAccessToken().getTokenValue();
	}

	/**
	 * Save OAuth2 User to Our Database
	 * 
	 * @param client
	 * @param oAuthToken
	 */
	private void saveOAuth2User(OAuth2AuthorizedClient client, OAuth2AuthenticationToken oAuthToken) {
		Object principal = oAuthToken.getPrincipal();
		if (principal instanceof DefaultOAuth2User) {
			String username = null != ((DefaultOAuth2User) principal).getAttribute("email")
					? ((DefaultOAuth2User) principal).getAttribute("email")
					: ((DefaultOAuth2User) principal).getAttribute("login");

			User user = userRepo.findByUsername(username);

			if (null == user) {
				user = ClassObjFactory.newInstance(User.class);
				user.setFullName(((DefaultOAuth2User) principal).getAttribute("name"));
				user.setUsername(username);
				user.setUserStatus(AuthConstant.USER_STATUS_ACTIVE);
				user = userRepo.save(user);

				UserAttribute userAttr = ClassObjFactory.newInstance(UserAttribute.class);
				userAttr.setProvider(CommonOAuth2Provider.GITHUB.name());
				userAttr.setUsername(user.getUsername());
				userAttributeRepo.save(userAttr);
			}
		} else {
			throw new SvcException("Failed to login");
		}
	}
}
