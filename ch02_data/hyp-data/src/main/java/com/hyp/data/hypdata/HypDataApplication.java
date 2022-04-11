package com.hyp.data.hypdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.hyp.data.hypdata.repository")
@EntityScan(basePackages = "com.hyp.data.hypdata.entity")
public class HypDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(HypDataApplication.class, args);
	}

}
