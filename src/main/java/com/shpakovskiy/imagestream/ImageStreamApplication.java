package com.shpakovskiy.imagestream;

import com.shpakovskiy.imagestream.data.WeirdLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImageStreamApplication {
	public static void main(String[] args) {
		SpringApplication.run(ImageStreamApplication.class, args);
		WeirdLogger.info("Application started successfully");
	}
}