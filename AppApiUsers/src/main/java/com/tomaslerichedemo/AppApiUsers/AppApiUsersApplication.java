package com.tomaslerichedemo.AppApiUsers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AppApiUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApiUsersApplication.class, args);
		System.out.println("Wassup, man?");
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
