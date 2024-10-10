package com.truecaller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TruecallerApplication {

	public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "secrets");
        SpringApplication.run(TruecallerApplication.class, args);
	}

}
