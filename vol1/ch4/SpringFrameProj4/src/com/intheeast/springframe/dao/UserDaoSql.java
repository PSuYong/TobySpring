package com.intheeast.springframe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.intheeast.springframe.domain.User;

public class UserDaoSql{
	
//	private DataSource dataSource;
//	dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
//	dataSource.setUrl("jdbc:mysql://localhost:3306/testdb?characterEncoding=UTF-8");
//	dataSource.setUsername("root");
//	dataSource.setPassword("1234");
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	
	public void add(final User user) throws SQLException {
		
		Connection c = this.dataSource.getConnection();

		PreparedStatement ps = 
				c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
						

		ps.executeUpdate();

		ps.close();
		c.close();
						
	}

}

