package com.umbrellait.carshop_camunda;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude =  { SecurityAutoConfiguration.class })
@AllArgsConstructor
public class CarshopCamundaApplication {


	public static void main(String[] args) {
		SpringApplication.run(CarshopCamundaApplication.class, args);
	}

}
