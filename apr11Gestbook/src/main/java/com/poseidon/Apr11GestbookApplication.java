package com.poseidon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Apr11GestbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(Apr11GestbookApplication.class, args);
	}

}
