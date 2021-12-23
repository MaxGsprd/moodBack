package com.mood.mood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
// import org.springframework.context.annotation.Bean;
// import org.springframework.web.client.RestTemplate;
// import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class MoodApplication {

	@Bean
	public RestTemplate getRestRemplate(){
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(MoodApplication.class, args);
	}

}
