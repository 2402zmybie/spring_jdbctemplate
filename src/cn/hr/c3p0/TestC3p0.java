package cn.hr.c3p0;

import org.junit.Test;
import org.junit.validator.PublicClassValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestC3p0 {
	

	
	@Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext("all_schema_application.xml");
		UserService userService =  (UserService) context.getBean("useService");
		userService.add();
		
	}
}
