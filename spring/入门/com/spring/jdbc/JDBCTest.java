package com.spring.jdbc;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class JDBCTest {
	
	private ApplicationContext atx=null;
	private JdbcTemplate jdbcTemplate;
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	{
		atx=new ClassPathXmlApplicationContext("jdbc.xml");
		jdbcTemplate = (JdbcTemplate) atx.getBean("jdbcTemplate");
		
		namedParameterJdbcTemplate= atx.getBean(NamedParameterJdbcTemplate.class);
		
	}
	
	/**
	 *插入数据
	 * 具名参数：自定义参数名，进行指定，不用对应位置
	 */
	@Test
	public void testNamedParameterJdbcTemplate() {
		String sql="INSERT INTO employeedata(last_name, email, dept_id) VALUES(:ln, :email, :deptid)";
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ln", "GG");
		paramMap.put("email", "GG@163.com");
		paramMap.put("deptid", "103");
		
		namedParameterJdbcTemplate.update(sql, paramMap);		
	}
	
	
	/**
	 * 实体类集合查询?
	 * 与单个查询差异：1可以用不等号限定输出id范围
	 * 			   2.使用List<Employee>列表
	 */
	@Test
	public void testQueryForList() {
		String sql = "SELECT id ,last_name lastName, email FROM employeedata WHERE 3 >id > ? ";
		RowMapper<Employee> rowMapper= new BeanPropertyRowMapper<>(Employee.class);
		List<Employee> employees= jdbcTemplate.query(sql, rowMapper,0);
		System.out.println(employees);
		
	}
	
	/**
	 * 从数据库中获取一条记录，单例查询
	 * 需调用JdbcTemplate.queryForObject(String sql, RowMapper<Employee> rowMapper)
	 * Rowmapping为行映射，
	 * 不支持级联属性查询，仅是JDBC下一工具，不支持ORM框架
	 */
	@Test
	public void testQueeryForObject() {
		String sql = "SELECT id, last_name lastName, email ,dept_id as \"department.id\" FROM employeedata WHERE id=?";
		RowMapper<Employee> rowMapper=new BeanPropertyRowMapper<>(Employee.class);
		Employee employee=jdbcTemplate.queryForObject(sql, rowMapper,1);
		System.out.println(employee);
		
		
		/**以下调用为JdbcTemplate.queryForObject(String sql, Class<Long> requiredType)
		 * 与上面调用用所差别，可用作统计计数表大小
		 */
		
		String sqlCount = "SELECT count(id) FROM employeedata";
		long count = jdbcTemplate.queryForObject(sqlCount, Long.class);
		System.out.println(count);

	}
	
	
	/**
	 * 批量执行增删改
	 */
	@Test
	public void testBatchUpdate() {
		String sql="INSERT INTO employeedata(last_name, email, dept_id) VALUES(?,?,?)";
		
		List<Object[]> batchArgs=new ArrayList<>();
		batchArgs.add(new Object[] {"AAq","AA@163.com",105});
		batchArgs.add(new Object[] {"BBq","BB@163.com",104});
		batchArgs.add(new Object[] {"CCq","CC@163.com",102});
		batchArgs.add(new Object[] {"DDq","DD@163.com",101});
		batchArgs.add(new Object[] {"EEq","EE@163.com",103});
		
		jdbcTemplate.batchUpdate(sql, batchArgs);
	}
	
	
	
	/**
	 * 执行增删改 INSERT, DELETE, UPDATE
	 */
	@Test
	public void testUpdate() {
		String sql="UPDATE employeedata SET last_name = ? WHERE id=?";
		jdbcTemplate.update(sql, "Nike",3);
	}

	@Test
	public void testDataSource() throws SQLException {
		DataSource dataSource=atx.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
		
	}

}
