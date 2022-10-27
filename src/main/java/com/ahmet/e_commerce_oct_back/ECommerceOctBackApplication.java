package com.ahmet.e_commerce_oct_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ECommerceOctBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceOctBackApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry
					.addMapping("/api/v1/***")
						.allowedOrigins("*")
						.allowCredentials(true)
						.allowedHeaders("Accept", "X-Requested-With","Cache-Control", "Authorization", "Content-Type", "apikey", "tenantId")
						.allowedMethods("GET", "POST", "PUT", "DELETE");
			}
		};
	}

}
