package com.intheeast.springframe.dao;

import java.sql.SQLException;

import com.intheeast.springframe.domain.User;


public class UserDaoTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		UserDao dao = new UserDaoFactory().userDao();
		//main에서 직접적인 접근을 했다면 이제는 그것을 userdaofactory에 위임함

		User user = new User();
		user.setId("whiteship");
<<<<<<< HEAD:vol1/ch1/SpringFrameProj1-4-2/src/com/kitec/springframe/dao/UserDaoTest.java
		user.setName("��⼱");
=======
		user.setName("백기선");
>>>>>>> main:vol1/ch1/SpringFrameProj1-4-2/src/com/intheeast/springframe/dao/UserDaoTest.java
		user.setPassword("married");

		dao.add(user);
			
<<<<<<< HEAD:vol1/ch1/SpringFrameProj1-4-2/src/com/kitec/springframe/dao/UserDaoTest.java
		System.out.println(user.getId() + " ��� ����");
=======
		System.out.println(user.getId() + "\n등록 성공");
>>>>>>> main:vol1/ch1/SpringFrameProj1-4-2/src/com/intheeast/springframe/dao/UserDaoTest.java
		
		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
			
<<<<<<< HEAD:vol1/ch1/SpringFrameProj1-4-2/src/com/kitec/springframe/dao/UserDaoTest.java
		System.out.println(user2.getId() + " ��ȸ ����");
=======
		System.out.println(user2.getId() + "\n조회 성공");
>>>>>>> main:vol1/ch1/SpringFrameProj1-4-2/src/com/intheeast/springframe/dao/UserDaoTest.java
	}

}
