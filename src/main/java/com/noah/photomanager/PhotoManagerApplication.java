package com.noah.photomanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class PhotoManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoManagerApplication.class, args);
	}

}
