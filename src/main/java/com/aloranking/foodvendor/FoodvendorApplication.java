package com.aloranking.foodvendor;

import com.aloranking.foodvendor.repositories.AuthUserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = AuthUserRepository.class)
@EnableJpaAuditing
public class FoodvendorApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodvendorApplication.class, args);
	}

}
