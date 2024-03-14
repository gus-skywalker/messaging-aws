package com.lesmonades.messaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class MessagingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessagingApplication.class, args);
	}

}
