package com.project.management.devboard;

import com.project.management.devboard.sandbox.EasterEgg;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DevboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevboardApplication.class, args);
	}


	@Bean
	public EasterEgg createEasterEggBean(){
		return new EasterEgg("qwerty1234");
	}

}
