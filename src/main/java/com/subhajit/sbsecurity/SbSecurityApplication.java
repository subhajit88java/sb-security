package com.subhajit.sbsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SbSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbSecurityApplication.class, args);
	}

}
