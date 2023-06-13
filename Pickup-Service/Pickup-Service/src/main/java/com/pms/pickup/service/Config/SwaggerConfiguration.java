package com.pms.pickup.service.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfiguration {
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("PICKUP-SERVICE").version("App Version")
				.description("This is for PICKUP-SERVICE Testing ").termsOfService("http://swagger.io/terms/"));
	}
}
