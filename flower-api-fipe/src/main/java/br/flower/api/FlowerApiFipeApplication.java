package br.flower.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync()
@SpringBootApplication
public class FlowerApiFipeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlowerApiFipeApplication.class, args);
	}

}
