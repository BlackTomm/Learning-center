package com.spring.tx;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("bookShopService")
public class BookShopService {
	@Autowired
	private BookShopDao bookShopDao=null;
	
	/**事务间的传播：比如如何在账户余额有限的情况下购买更多地书籍
	 * propagation包含七种选择：REQUIRED为事务间相互关联，调用相关，必须全部成功
	 * REQUIRES_NEW表示当前事务不成功可以先挂起再调用另一个事务
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW,
			isolation = Isolation.READ_UNCOMMITTED,
			timeout = 3)
	public void purchase(String username, String book_id) {
		
		try {
			Thread.sleep(5000); //当线程休眠时间大于延时，事务回滚，不会改动			
		} catch (Exception e) {
			// TODO: handle exception
		}
		int price=bookShopDao.findBookPriceById(book_id);
		bookShopDao.updateBookStock(book_id);
		bookShopDao.updateUserAccount(username, price);
		
	}
	

	public BookShopService() {
		// TODO Auto-generated constructor stub
	}

}
