package com.bonvoyage.attraction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.bonvoyage")
public class BonvoyageWApplication {

	public static void main(String[] args) {
		SpringApplication.run(BonvoyageWApplication.class, args);
	}

}
