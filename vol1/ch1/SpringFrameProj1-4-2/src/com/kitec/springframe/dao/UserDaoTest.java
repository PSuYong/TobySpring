package com.kitec.springframe.dao;

import java.sql.SQLException;

import com.kitec.springframe.domain.User;


public class UserDaoTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		UserDao dao = new UserDaoFactory().userDao();
		//main에서 직접적인 접근을 했다면 이제는 그것을 userdaofactory에 위임함

		User user = new User();
		user.setId("whiteship");
		user.setName("��⼱");
		user.setPassword("married");

		dao.add(user);
			
		System.out.println(user.getId() + " ��� ����");
		
		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
			
		System.out.println(user2.getId() + " ��ȸ ����");
	}

}
