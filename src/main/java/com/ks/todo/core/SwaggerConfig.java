package com.ks.todo.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

	private static final List<Server> servers = new ArrayList<>();
	
	static {
		Server devServer = new Server();
		devServer.setUrl("http://localhost:8081");
		devServer.setDescription("Development Server");
		servers.add(devServer);
	}

	@Bean
	public OpenAPI openAPIConfig() {
		Contact contact = new Contact();
		contact.setEmail("kenshencu99@gmail.com");
		contact.setName("Ken Shen");

		License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

		Info info = new Info().title("TODO App Swagger").version("1.0").contact(contact).license(mitLicense);

		return new OpenAPI().info(info).servers(servers);
	}
}
