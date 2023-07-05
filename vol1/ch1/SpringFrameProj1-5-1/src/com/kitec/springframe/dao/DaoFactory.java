package com.kitec.springframe.dao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//어노테이션 기반 configuration 메타데이터
//스프링 IOC 컨테이너( AnnotationConfigApplicationContext 
//                1-4-2에서 user daoFactory랑 비슷한거)에게 전달될 정보들
public class DaoFactory {
	
	@Bean
	//빈이라는 어노테이션을 통해 빈 IOC 컨테이너에 이를 전달함 
	public UserDao userDao() {
		return new UserDao(connectionMaker());
	}	
	
	@Bean
	public ConnectionMaker connectionMaker() {
		return new DConnectionMaker();
	}

}
