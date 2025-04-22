package com.poseidon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling //스케쥴러 사용
public class Apr11GestbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(Apr11GestbookApplication.class, args);
	}

}
