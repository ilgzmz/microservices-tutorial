package com.user.svc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/*
Sets this class as a Feign Client
*/
@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class UserSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserSvcApplication.class, args);
	}

}
