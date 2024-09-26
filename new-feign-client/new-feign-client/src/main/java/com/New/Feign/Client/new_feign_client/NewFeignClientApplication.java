package com.New.Feign.Client.new_feign_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class NewFeignClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewFeignClientApplication.class, args);
	}

}
