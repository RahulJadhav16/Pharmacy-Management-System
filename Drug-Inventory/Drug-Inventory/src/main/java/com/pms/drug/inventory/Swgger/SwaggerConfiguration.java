package com.pms.drug.inventory.Swgger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfiguration {
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("DRUG-INVENTORY-SERVICE").version("App Version")
				.description("").termsOfService("http://swagger.io/terms/"));
	}
}
