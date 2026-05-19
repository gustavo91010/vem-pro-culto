package com.ajudaqui.vem_pro_culto_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class VemProCultoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VemProCultoApiApplication.class, args);
	}

}
