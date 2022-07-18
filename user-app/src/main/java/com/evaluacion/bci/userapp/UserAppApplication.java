package com.evaluacion.bci.userapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAppApplication.class, args);
	}

	@Bean
	public String dummy(){
		return "algo";
	}
}
