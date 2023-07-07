package com.intheeast.springframe.dao;

import java.sql.Connection;
<<<<<<< HEAD
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
						
=======
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.intheeast.springframe.domain.User;

// 
public class UserDaoSql {
	
	private Connection getConnection() throws ClassNotFoundException,
	SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb?characterEncoding=UTF-8", 
				"root",
				"1234");
		return c;
	}

	
	public void add(User user) throws ClassNotFoundException, SQLException {
		Connection c = getConnection();

		PreparedStatement ps = c.prepareStatement(
			"insert into users(id, name, password) values(?,?,?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
>>>>>>> e31ac4b86ce9f669ccd3e2c27afad49cd47a0d38

		ps.executeUpdate();

		ps.close();
		c.close();
<<<<<<< HEAD
						
	}

}

=======
		
	}
}
>>>>>>> e31ac4b86ce9f669ccd3e2c27afad49cd47a0d38
