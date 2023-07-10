package com.intheeast.springframe.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.intheeast.springframe.domain.User;
import com.mysql.cj.exceptions.MysqlErrorNumbers;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestDaoFactory.class})
public class UserDaoTest {	
	 
	@Autowired UserDao dao; 
	@Autowired UserDaoSql userDaoSql;
	@Autowired DataSource dataSource;
	@Autowired UserDaoSql userDaosql;
	
	private User user1;
	private User user2;
	private User user3;
	
		
	@BeforeEach
	public void setUp() {	
		
		this.user1 = new User("gyumee", "sungkim", "springno1");
		this.user2 = new User("leegw700", "brucelee", "springno2");
		this.user3 = new User("bumjin", "haechoi", "springno3");
	}
	
	@Test
	public void addAndGet() throws SQLException, ClassNotFoundException {				
		dao.deleteAll();
		assertEquals(dao.getCount(), 0);
		
		dao.add(user1);
		dao.add(user2);
		assertEquals(dao.getCount(), 2);
		
		Optional<User> Optuserget1 = dao.get(user1.getId());
		if(!Optuserget1.isEmpty()) {
			User userget = Optuserget1.get();
			assertEquals(user1.getName(), userget.getName());
			assertEquals(user1.getPassword(), userget.getPassword());
		}
		
		Optional<User> Optuserget2 = dao.get(user2.getId());
		if(!Optuserget2.isEmpty()) {
			User userget = Optuserget2.get();
			assertEquals(user2.getName(), userget.getName());
			assertEquals(user2.getPassword(), userget.getPassword());
		}			
	}
	
	@Test
	public void count() throws SQLException, ClassNotFoundException {		
		dao.deleteAll();
		assertEquals(dao.getCount(), 0);

		dao.add(user1);
		assertEquals(dao.getCount(), 1);
		
		dao.add(user2);
		assertEquals(dao.getCount(), 2);
		
		dao.add(user3);
		assertEquals(dao.getCount(), 3);		
	}
	
	@Test
	public void getUserFailure() throws SQLException, ClassNotFoundException {		
		dao.deleteAll();
		assertEquals(dao.getCount(), 0);		
		
		Optional<User> Optuserget = dao.get("unknown_id");
		assertTrue(Optuserget.isEmpty());		
	}
	
	@Test
	public void getAll() throws SQLException  {
		dao.deleteAll();
		List<User> users0 = dao.getAll();
		assertEquals(users0.size(), 0);
		
		dao.add(user1); 
		List<User> users1 = dao.getAll();
		assertEquals(users1.size(), 1);
		checkSameUser(user1, users1.get(0));
		
		dao.add(user2); 
		List<User> users2 = dao.getAll();
		assertEquals(users2.size(), 2);
		checkSameUser(user1, users2.get(0));  
		checkSameUser(user2, users2.get(1));
		
		dao.add(user3); 
		List<User> users3 = dao.getAll();
		assertEquals(users3.size(), 3);	
		checkSameUser(user3, users3.get(0));  
		checkSameUser(user1, users3.get(1));  
		checkSameUser(user2, users3.get(2)); 
		
		
		return ;
	}
	
	private void checkSameUser(User user1, User user2) {
		assertEquals(user1.getId(), user2.getId());
		assertEquals(user1.getName(), user2.getName());
		assertEquals(user1.getPassword(), user2.getPassword());
	}
	
	@Test
	public void duplciateKey() throws SQLException {
		dao.deleteAll();
		
		dao.add(user1);
		assertThrows(DuplicateKeyException.class, () -> dao.add(user1));
	}
	
	@Test
	public void sqlExceptionTranslate() {
		dao.deleteAll();
		
		try {
			dao.add(user1);
			dao.add(user1);
		} //org.springframework.dao.DuplicateKeyException
		catch(DuplicateKeyException ex) {
			SQLException sqlEx = (SQLException)ex.getCause();
			SQLExceptionTranslator set = new SQLErrorCodeSQLExceptionTranslator(this.dataSource);			
			DataAccessException transEx = set.translate(null, null, sqlEx);
			assertEquals(DuplicateKeyException.class, transEx.getClass());
		}
	}
	
	@Test
<<<<<<< HEAD
	public void sqlExceptionTranslate2() {
		dao.deleteAll();
		
		try {
			UserDaoSql add.(user1)
			UserDaoSql add.(user1)

		}//org.springframework.dao.DuplicateKeyException
		catch(SQLException ex) {
			//ex -> DuplicateKeyException 바꿔라
=======
	public void sqlExceptionTranslate2() throws ClassNotFoundException {
		dao.deleteAll();
		
		try {
			userDaoSql.add(user1);
			userDaoSql.add(user1);
		} //org.springframework.dao.DuplicateKeyException
		catch(SQLException ex) {
			System.out.println(ex);
			if (ex.getErrorCode() == MysqlErrorNumbers.ER_DUP_ENTRY)
				throw new DuplicateKeyException(ex.getMessage());
			else
				throw new RuntimeException(ex);
>>>>>>> e31ac4b86ce9f669ccd3e2c27afad49cd47a0d38
		}
	}

		
}
