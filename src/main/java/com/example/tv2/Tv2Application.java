package com.example.tv2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class Tv2Application {

	public static void main(String[] args) {
		SpringApplication.run(Tv2Application.class, args);
	}

	@Scope("singleton")
	CommandLineRunner run( //AppUserService userService
	){
		return args -> {
//			userService.saveRole(new Role(null , "ROLE_USER"));
			System.out.println("salam");

		} ;
	}


}
