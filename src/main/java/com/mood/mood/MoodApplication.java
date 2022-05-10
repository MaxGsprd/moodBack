package com.mood.mood;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MoodApplication {

	private static final String[] ALLOWED_ORIGINS = {"http://localhost:4200", "http://localhost", "http://localhost:8080", "*"};

	@Bean
	public RestTemplate getRestRemplate(){
		return new RestTemplate();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}


	public static void main(String[] args) {
		SpringApplication.run(MoodApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins(ALLOWED_ORIGINS)
						.allowedMethods("*")
						.allowedHeaders("*")
						.allowCredentials(false)
						.exposedHeaders("Authorization","Access-Control-Allow-Origin")
						.maxAge(-1)
				;
			}
		};
	}
}
