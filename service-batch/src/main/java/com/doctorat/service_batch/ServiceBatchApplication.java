package com.doctorat.service_batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ServiceBatchApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceBatchApplication.class, args);
	}
}