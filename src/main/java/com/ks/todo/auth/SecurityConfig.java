package com.ks.todo.auth;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	protected OAuth2GitHubAuthenticationFilter oAuth2GitHubAuthenticationFilter;

	@Value("${server.port}")
	private String serverPort;

	@Value("${spring.security.oauth2.client.registration.github.client-id}")
	private String gitHubClientId;

	@Bean
	@Order(1)
	public SecurityFilterChain asSecurityFilterChain(HttpSecurity http) throws Exception {
		OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

		http.getConfigurer(OAuth2AuthorizationServerConfigurer.class).oidc(Customizer.withDefaults());
		http.exceptionHandling(e -> e.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login")));

		return http.build();
	}

	@Bean
	@Order(2)
	public SecurityFilterChain appSecurityFilterChain(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
				.addFilterBefore(oAuth2GitHubAuthenticationFilter, OAuth2LoginAuthenticationFilter.class)
				.authorizeHttpRequests(request -> request.anyRequest().authenticated()).formLogin().disable()
				.oauth2Login(oauth2 -> oauth2.defaultSuccessUrl("/auth/login/success", true));

		return http.build();
	}

	@Bean
	public RegisteredClientRepository registeredClientRepository() {
		RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString()).clientId("client")
				.clientSecret("secret").scope(OidcScopes.OPENID).scope(OidcScopes.PROFILE)
				.redirectUri("http://localhost:" + serverPort + "/login/oauth2/code/github/" + gitHubClientId)
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE).build();

		return new InMemoryRegisteredClientRepository(registeredClient);
	}
}
