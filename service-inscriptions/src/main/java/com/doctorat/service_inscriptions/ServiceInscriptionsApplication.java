package com.doctorat.service_inscriptions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceInscriptionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceInscriptionsApplication.class, args);
	}
}