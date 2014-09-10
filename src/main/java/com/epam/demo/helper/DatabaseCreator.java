package com.epam.demo.helper;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

public class DatabaseCreator {
	
	private final DataSource dataSource;

	public DatabaseCreator(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	public void createDatabase() {
		try (Connection conn = dataSource.getConnection()) {

			ResourceDatabasePopulator rdp = new ResourceDatabasePopulator();
			rdp.setContinueOnError(false);
			rdp.addScript(new ClassPathResource("sql/createTables.sql"));
			rdp.populate(conn);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
