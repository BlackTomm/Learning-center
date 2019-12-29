package com.spring.tx_xml;


import java.util.Arrays;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTransactionTest {

	private ApplicationContext atx=null;
	private BookShopDao bookShopDao=null;
	private BookShopService bookShopService=null;
	private Cashier cashier=null;
	
	{
		atx=new ClassPathXmlApplicationContext("tx_xml.xml");
		bookShopDao=atx.getBean(BookShopDao.class);
		bookShopService=atx.getBean(BookShopService.class);
		cashier=atx.getBean(Cashier.class);
	}
	
//	@Test
//	public void testBookShopDaoUpdateUserAccount() {
//		
//		bookShopDao.updateUserAccount("mactalk", 50);
//	}
//	@Test
//	public void testBookShopDaoUpdateBookStock() {
//		bookShopDao.updateBookStock("1001");
//	}
//	
//	@Test
//	public void tesBookShopDaoFindPriceById() {
//		System.out.println(bookShopDao.findBookPriceById("1002"));
//	}
//	
//	@Test
//	public void testBookShopService() {
//		bookShopService.purchase("mactalk", "1001");
//	}
//	
//	@Test
//	public void testTransactionPropagation() {
//		cashier.checkout("mactalk", Arrays.asList("1001","1002"));
//	}

}
