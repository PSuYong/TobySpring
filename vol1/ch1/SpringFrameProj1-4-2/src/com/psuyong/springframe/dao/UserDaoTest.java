package com.psuyong.springframe.dao;

import java.sql.SQLException;

import com.psuyong.springframe.domain.User;


public class UserDaoTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		UserDao dao = new UserDaoFactory().userDao();
		//main에서 직접적인 접근을 했다면 이제는 그것을 userdaofactory에 위임함

		User user = new User();
		user.setId("whiteship");
		user.setName("��⼱");
		user.setName("백기선");
		user.setPassword("married");

		dao.add(user);
			
		System.out.println(user.getId() + " ��� ����");

		System.out.println(user.getId() + "\n등록 성공");

		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
		System.out.println(user2.getId() + " ��ȸ ����");

		System.out.println(user2.getId() + "\n조회 성공");
	}

}
