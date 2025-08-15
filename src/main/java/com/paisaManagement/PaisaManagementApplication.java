package com.paisaManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class PaisaManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaisaManagementApplication.class, args);
	}

}
