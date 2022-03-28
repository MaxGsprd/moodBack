package com.mood.mood;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class MoodApplication {

	@Bean
	public RestTemplate getRestRemplate(){
		//return new RestTemplate();
		RestTemplate template = new RestTemplate();
		// Exclude xml parsing converters to avoid parsing json data as xml
		List<HttpMessageConverter<?>> collect = template.getMessageConverters().stream().filter(m -> !(m instanceof MappingJackson2XmlHttpMessageConverter)).collect(Collectors.toList());
		template.setMessageConverters(collect);
		return template;
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}


	public static void main(String[] args) {
		SpringApplication.run(MoodApplication.class, args);
	}

}
