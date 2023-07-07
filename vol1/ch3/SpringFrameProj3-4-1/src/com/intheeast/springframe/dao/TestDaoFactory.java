package com.intheeast.springframe.dao;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
public class TestDaoFactory {
	//어떤 빈객체를 관리할지 어떻게 DI를 할지에 대한 메타데이터
	//이를 컨피규레이션이라고 함!  
	@Bean
	public DataSource dataSource() {
		
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		
		dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
		dataSource.setUrl("jdbc:mysql://localhost:3306/testdb?characterEncoding=UTF-8");
		dataSource.setUsername("root");
		dataSource.setPassword("1234");

		return dataSource;
	}

	@Bean
	public JdbcContext jdbcContext() {
		JdbcContext jdbcContext = new JdbcContext();
		jdbcContext.setDataSource(dataSource());
		return jdbcContext;
	}

	@Bean
	public UserDao userDao() {
		UserDao userDao = new UserDao();
		userDao.setJdbcContext(jdbcContext());
		return userDao;
	}

}

