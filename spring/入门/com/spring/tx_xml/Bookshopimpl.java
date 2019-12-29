package com.spring.tx_xml;

import org.springframework.jdbc.core.JdbcTemplate;


public class Bookshopimpl implements BookShopDao {
	
	private JdbcTemplate jdbcTemplate;
	
	//去掉autowired自动装配后需要配置set方法
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
		
	}

	//根据书号获取单价
	@Override
	public int findBookPriceById(String book_id) {
		String sql = "SELECT price FROM book WHERE book_id=?";
		return jdbcTemplate.queryForObject(sql, Integer.class,book_id);
	}
	
	//更新书库存->改
	public void updateBookStock(String book_id) {
		//检查库存是否有剩余，没有则抛出啊异常
		
		String sql2="SELECT stock FROM book_stock WHERE book_id=?"; 
		int remain_stock=jdbcTemplate.queryForObject(sql2, Integer.class, book_id);
		if(remain_stock==0) { 
			throw new BookStockException("库存不足 !!!!!!!! "); 
		}
	 
		//购买一本即更新库存
		String sql="UPDATE book_stock SET stock = stock-1 WHERE book_id=?";
		jdbcTemplate.update(sql,book_id);
	}
	
	//用户账户管理：购买书籍则使用balance-price->改
	public void updateUserAccount(String username, int price) {
		//检查当前账户余额是否足够买该书
		String sql2="SELECT balance FROM account WHERE username=?";
		int current_balance= jdbcTemplate.queryForObject(sql2, Integer.class, username);
		if(current_balance <price) {
			throw new UserAccountException("余额不足");
		}
		String sql = "UPDATE account SET balance=balance-? WHERE username=?";
		jdbcTemplate.update(sql, price, username);
	}

}
