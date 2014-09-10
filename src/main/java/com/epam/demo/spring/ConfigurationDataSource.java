package com.epam.demo.spring;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.epam.demo.helper.DatabaseCreator;

@Configuration
public class ConfigurationDataSource {

	private static String dbUrl = "jdbc:mysql://localhost/demo";
	private static String username = "root";
	private static String password = "1234";

	@Bean
	public DataSource dataSource() {
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return new DriverManagerDataSource(dbUrl, username, password);
	}

	@Bean
	public DatabaseCreator databaseCreator(DataSource dataSource) {
		return new DatabaseCreator(dataSource);
	}

}
