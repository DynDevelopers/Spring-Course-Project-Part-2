package com.upgrad.hirewheels;

import com.upgrad.hirewheels.services.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class HirewheelsApplication {

//	@Autowired
//	private InitService initService;

	public static void main(String[] args) {
		SpringApplication.run(HirewheelsApplication.class, args);
	}

//	@PostConstruct
//	private void initService() {
//
//	}

	@Bean
	CommandLineRunner init (InitService initService) {
		return args -> {
			initService.populateRole();
			initService.populateUserAdmin();
			initService.populateCity();
			initService.populateLocation();
			initService.populateFuelType();
			initService.populateVehicleCategory();
			initService.populateVehicleSubCategory();
		};
	}
}
