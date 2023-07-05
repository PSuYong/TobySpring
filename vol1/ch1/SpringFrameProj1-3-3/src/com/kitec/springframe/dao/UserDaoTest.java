package com.kitec.springframe.dao;

import java.sql.SQLException;

import com.kitec.springframe.domain.User;


public class UserDaoTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ConnectionMaker connectionMaker = new DConnectionMaker();
		UserDao dao = new UserDao(connectionMaker);

		User user = new User();
		user.setId("whiteship");
		user.setName("IGONAN");
		user.setPassword("married");

		dao.add(user);
			
		System.out.println(user.getId() + " ��� ����");
		
		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
			
		System.out.println(user2.getId() + " ��ȸ ����");
	}

}
