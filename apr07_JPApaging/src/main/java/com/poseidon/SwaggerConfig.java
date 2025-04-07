package com.poseidon;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

	@Bean
	OpenAPI openAPI() {
		return new OpenAPI().components(new Components()).info(apiInfo());
	}

	private Info apiInfo() {
		return new Info().title("스웨거 연습").description("덕성여대").version("1.1");
	}

}
