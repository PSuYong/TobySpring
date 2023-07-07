package com.intheeast.springframe5.dao;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
public class DaoFactory {
	
	@Bean
	public DataSource dataSource() {
		
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		
//		dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
//		dataSource.setUrl("jdbc:mysql://localhost:3306/sbdt_db1?characterEncoding=UTF-8");
//		dataSource.setUsername("root");
//		dataSource.setPassword("1234");
		
		dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
		dataSource.setUrl("jdbc:mysql://192.168.0.47/sbdt_db1?characterEncoding=UTF-8");
		dataSource.setUsername("kkh");
		dataSource.setPassword("1234");

		return dataSource;
	}

	@Bean
	public UserDao userDao() {
		UserDao userDao = new UserDao();
		userDao.setDataSource(dataSource());
		return userDao;
	}
	//DI

}
