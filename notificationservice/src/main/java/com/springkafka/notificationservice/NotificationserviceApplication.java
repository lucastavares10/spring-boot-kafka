package com.springkafka.notificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotificationserviceApplication {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/api");

		SpringApplication.run(NotificationserviceApplication.class, args);
	}

}
