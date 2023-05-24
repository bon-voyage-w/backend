package com.bonvoyage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class BonvoyageWApplication {
	public static void main(String[] args) {
		SpringApplication.run(BonvoyageWApplication.class, args);
	}
}
