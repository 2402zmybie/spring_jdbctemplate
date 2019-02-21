package cn.hr.jdbc;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.validator.PublicClassValidator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class JdbcTemplateDemo1 {
	
	private static DriverManagerDataSource dataSource;
	private static JdbcTemplate jdbcTemplate;
	static {
		dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql:///test");
		dataSource.setUsername("root");
		dataSource.setPassword("123");
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Test
	public void add() {
		String sql = "insert user values(?,?)";
		int row = jdbcTemplate.update(sql, "liu", "123");
		System.out.println(row);
	}
	
	@Test
	public void delete() {
		String sql = "delete from user where username = ?";
		int row = jdbcTemplate.update(sql,"he");
		System.out.println(row);
	}
	
	@Test
	public void update() {
		String sql = "update user set username=? where username=?";
		int row = jdbcTemplate.update(sql,"wei","liu");
		System.out.println(row);
	}
	


}
