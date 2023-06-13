package com.pms.AdminMicroservice.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfiguration {
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("ADMIN-SERVICE").version("App Version")
				.description("DOCTOR-SERVICE is for doctor user").termsOfService("http://swagger.io/terms/"));
	}
}
