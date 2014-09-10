package com.epam.demo.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.epam.demo.helper.DatabaseCreator;

@Configuration 
@ComponentScan("com.epam.demo") 
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	DatabaseCreator databaseCreator;
	
	@PostConstruct
	public void gen() {
		databaseCreator.createDatabase();
	}
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
}
