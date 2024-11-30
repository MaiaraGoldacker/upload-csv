package com.upload.demo.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
@io.swagger.v3.oas.annotations.security.SecurityScheme(
		  type = SecuritySchemeType.HTTP,
		  name = "basicAuth",
		  scheme = "basic")
public class SwaggerConfig {
	
	String securitySchemeName = "basicAuth";

	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder()
				.group("business")
				.pathsToMatch("/**")
				.packagesToScan("com.upload.demo.controller")
				.build();
	}

	@Bean
	public OpenAPI apiInfo() {
		return new OpenAPI()
				.info(new Info().title("Test")
						.description("Maiara's upload csv ")
						.version("1.0")
					)
				 .components(new Components())
				.servers(List.of(new Server().url("/").description("UPLOAD")));
	}

}
