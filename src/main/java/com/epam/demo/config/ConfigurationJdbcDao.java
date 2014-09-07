package com.epam.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.epam.demo.dao.FoodDao;
import com.epam.demo.dao.JdbcFoodDaoImpl;

@Configuration
public class ConfigurationJdbcDao {
	
	@Bean
	public FoodDao foodDao() {
		return new JdbcFoodDaoImpl();
	}

}
