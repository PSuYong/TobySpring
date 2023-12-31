package com.intheeast.springframe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

import com.intheeast.springframe.domain.User;

public class UserDao {
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
<<<<<<< HEAD:vol1/ch3/SpringFrameProj3-3/src/com/intheeast/springframe5/dao/UserDao.java
	//jdbcContextWithStatementStrategy = 컨텍스트
=======
	
	public DataSource getDataSource() {
		return this.dataSource;
	}
	
>>>>>>> c39dbaa86f4e286c40fb8a8ea3a2a47b88e63ab1:vol1/ch3/SpringFrameProj3-3/src/com/intheeast/springframe/dao/UserDao.java
	public void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException {
		//전략패턴을 적용했다.
		//클라이언트가 컨텍스트를 사용한다.
		Connection c = null;
		PreparedStatement ps = null;

		try {
			c = dataSource.getConnection();

			ps = stmt.makePreparedStatement(c);//전략
		
			ps.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			if (ps != null) { try { ps.close(); } catch (SQLException e) {} }
			if (c != null) { try {c.close(); } catch (SQLException e) {} }
		}
	}
	
	public void add(final User user) throws SQLException {
		jdbcContextWithStatementStrategy(
				
				new StatementStrategy() {			
					public PreparedStatement makePreparedStatement(Connection c)throws SQLException
					{
						PreparedStatement ps = 
							c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
						ps.setString(1, user.getId());
						ps.setString(2, user.getName());
						ps.setString(3, user.getPassword());
						
						return ps;
					}
				}
		);
	}
	
	public User get(String id) throws ClassNotFoundException, SQLException {
		
		Connection c = this.dataSource.getConnection();
		
		PreparedStatement ps = c
				.prepareStatement("select * from users where id = ?");
		ps.setString(1, id);

		ResultSet rs = ps.executeQuery();
		
		User user = null;
		if (rs.next()) {
			user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
		}
		
		rs.close();
		ps.close();
		c.close();
		
		if (user == null) throw new EmptyResultDataAccessException(1);

		return user;
	}
	
	public void deleteAll() throws SQLException {
		jdbcContextWithStatementStrategy(
			new StatementStrategy() {
				public PreparedStatement makePreparedStatement(Connection c) throws SQLException 
				{
					return c.prepareStatement("delete from users");
				}
			}
		);
	}
	
	public int getCount() throws SQLException, ClassNotFoundException  {
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = this.dataSource.getConnection();
			
			ps = c.prepareStatement("select count(*) from users");
			rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			throw e;
		} finally {
			if ( rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {					
				}
			}			
			if ( ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {					
				}
			}
			if ( c!= null) {
				try {
					c.close();
				} catch (SQLException e) {					
				}
			}			
		}		
	}

}
