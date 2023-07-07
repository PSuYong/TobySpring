package com.kitec.springframe.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.psuyong.springframe.dao.ConnectionMaker;

public class NConnectionMaker implements ConnectionMaker {

	@Override
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/sbdt_db?characterEncoding=UTF-8", 
				"root",
				"1234");
		return c;
	}

}
