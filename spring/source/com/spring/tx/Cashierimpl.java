package com.spring.tx;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("service")
public class Cashierimpl implements Cashier {
	
	@Autowired
	private BookShopService bookShopService;
	
	@Override
	public void checkout(String username, List<String> book_ids) {
		for(String book_id: book_ids) {
			bookShopService.purchase(username, book_id);			
		}
		
	}

}
