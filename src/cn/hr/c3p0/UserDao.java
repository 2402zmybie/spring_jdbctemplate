package cn.hr.c3p0;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;

@Component(value="userDao")
public class UserDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void add() {
		String sql = "insert user values(?,?)";
		int row = jdbcTemplate.update(sql,"run","2556");
		System.out.println("UserDao add..." + row);
		
	}
	
}
