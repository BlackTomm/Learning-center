package com.spring.tx_xml;

public class BookShopService {
	
	private BookShopDao bookShopDao=null;
	public void setBookShopDao(BookShopDao bookShopDao) {
		this.bookShopDao = bookShopDao;
	}
	
	/**事务间的传播：比如如何在账户余额有限的情况下购买更多地书籍
	 * propagation包含七种选择：REQUIRED为事务间相互关联，调用相关，必须全部成功
	 * REQUIRES_NEW表示当前事务不成功可以先挂起再调用另一个事务
	 */
	/*
	 * @Transactional(propagation = Propagation.REQUIRES_NEW, isolation =
	 * Isolation.READ_UNCOMMITTED, timeout = 3)
	 */
	public void purchase(String username, String book_id) {

		int price=bookShopDao.findBookPriceById(book_id);
		bookShopDao.updateBookStock(book_id);
		bookShopDao.updateUserAccount(username, price);
		
	}
	

	public BookShopService() {
		// TODO Auto-generated constructor stub
	}

}
