package com.spring.tx;

import java.util.List;

public interface Cashier {

	public void checkout(String username, List<String> book_ids);
}
