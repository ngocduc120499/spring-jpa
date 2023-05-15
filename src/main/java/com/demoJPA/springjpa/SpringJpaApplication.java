package com.demoJPA.springjpa;

import com.demoJPA.springjpa.models.RegisterRequest;
import com.demoJPA.springjpa.service.AuthenticationService;
import com.demoJPA.springjpa.utils.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.demoJPA.springjpa.models.Role.*;


@SpringBootApplication
public class SpringJpaApplication {
	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(SpringJpaApplication.class, args);


	}
	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService authenticationService,
			JwtUserDetailsService jwtUserDetailsService
	) {
		return args -> {

//			var admin = RegisterRequest.builder()
//					.firstname("Admin")
//					.lastname("Admin")
//					.email("admin@mail.com")
//					.password("password")
//					.role(ADMIN)
//					.build();
//			System.out.println("Admin token: " + authenticationService.register(admin).getToken());
//
//			var manager = RegisterRequest.builder()
//					.firstname("Admin")
//					.lastname("Admin")
//					.email("manager@mail.com")
//					.password("password")
//					.role(MANAGER)
//					.build();
//			System.out.println("Manager token: " +  authenticationService.register(manager).getToken());

		};
	}
}
