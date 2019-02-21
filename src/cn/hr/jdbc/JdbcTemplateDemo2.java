package cn.hr.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.junit.validator.PublicClassValidator;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;



public class JdbcTemplateDemo2 {
	
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
	/**
	 * 查询数量
	 */
	@Test
	public void query1() {
		String sql = "select count(1) from user";
		int row = jdbcTemplate.queryForObject(sql, Integer.class);
		System.out.println(row);
	}
	/**
	 * 查询单个
	 */
	@Test
	public void query2() {
		String sql = "select * from user where username=?";
		User user =  jdbcTemplate.queryForObject(sql, new MyRowMapper(), "Ling");
		System.out.println(user);
	}
	
	/**
	 * 查询集合
	 */
	@Test
	public void query3() {
		String sql = "select * from user";
		List<User> lists=  jdbcTemplate.query(sql, new MyRowMapper());
		System.out.println(lists);
	}
	
	/**
	 * 原生实现jdbc的查询
	 */
	@Test
	public void testJDBC() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//加载驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//创建连接
			connection = DriverManager.getConnection("jdbc:mysql:///test","root","123");
			//编写sql语句
			String sql = "select * from user where username=?";
			//预编译sql
			preparedStatement = connection.prepareStatement(sql);
			//设置参数值
			preparedStatement.setString(1, "Ling");
			//执行sql
			resultSet = preparedStatement.executeQuery();
			//遍历结果集
			while(resultSet.next()) {
				//得到返回结果集
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");
				User user = new User();
				user.setUsername(username);
				user.setPassword(password);
				System.out.println(user);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭连接
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	


}


class MyRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		String username = rs.getString("username");
		String password = rs.getString("password");
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		return user;
	}
	
}
