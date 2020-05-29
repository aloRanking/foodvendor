package com.aloranking.foodvendor;

import com.aloranking.foodvendor.repositories.AuthUserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = AuthUserRepository.class)
@EnableJpaAuditing
@EnableSwagger2
public class FoodVendorApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodVendorApplication.class, args);
	}

	@Bean
	public Docket foodVendorApi(){
		return new Docket( DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.aloranking.foodvendor.controllers"))
				.build();
	}

}
