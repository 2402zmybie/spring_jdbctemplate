package cn.hr.c3p0;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public void add() {
		System.out.println("userService add.....");
		userDao.add();
	}
	       
}
