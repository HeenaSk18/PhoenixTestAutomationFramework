package com.database.model;

import static com.api.utils.DateTimeUtil.getTimeWithDaysAgo;

import com.api.constant.Model;
import com.api.constant.Product;
import com.api.request.model.CustomerProduct;
import com.database.dao.CustomerProductDao;

public class DemoRunner {

	public static void main(String[] args) {
//		CustomerDBModel customer = new CustomerDBModel("Jatin","Shharma","7878787878","","jatinshhrama@gmail.com","");
//		System.out.println(customer);
		
	CustomerProductDBModel customerProductDBModel=	CustomerProductDao.getProductInfoFromDB(128896);
	System.out.println(customerProductDBModel);
	
	CustomerProduct customerProduct = new CustomerProduct(getTimeWithDaysAgo(10), "12363731599999",
			"12363731599999", "12363731599999", getTimeWithDaysAgo(10), Product.NEXUS_2.getCode(),
			Model.NEXUS_2_BLUE.getCode());

	System.out.println(customerProduct);
	
	}

}
