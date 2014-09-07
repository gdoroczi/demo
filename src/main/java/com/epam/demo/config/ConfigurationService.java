package com.epam.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.epam.demo.dao.FoodDao;
import com.epam.demo.service.FoodService;

@Configuration
public class ConfigurationService {
	
	@Bean
	public FoodService foodService(FoodDao foodDao) {
		return new FoodService(foodDao);
	}

}
