package com.epam.demo.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.epam.demo.dao.FoodDao;
import com.epam.demo.dao.jdbcimpl.JdbcFoodDaoImpl;

@Configuration
public class ConfigurationJdbcDao {
	
	@Bean
	public FoodDao foodDao(DataSource datasource) {
		return new JdbcFoodDaoImpl(datasource);
	}

}
