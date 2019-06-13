package com.spring.tx_xml;


public interface BookShopDao {

	//根据书号获取单价
	public int findBookPriceById(String book_id);
	
	//更新书库存
	public void updateBookStock(String book_id);
	
	//用户账户管理：购买书籍则使用balance-price
	public void updateUserAccount(String username, int price);
}
