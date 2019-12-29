package com.spring.tx_xml;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public class Cashierimpl implements Cashier {
	
	
	private BookShopService bookShopService;
	public void setBookShopService(BookShopService bookShopService) {
		this.bookShopService = bookShopService;
	}
	
	@Override
	public void checkout(String username, List<String> book_ids) {
		for(String book_id: book_ids) {
			bookShopService.purchase(username, book_id);			
		}
		
	}

}
