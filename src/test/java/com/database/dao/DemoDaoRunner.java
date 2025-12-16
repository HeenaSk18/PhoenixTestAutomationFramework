package com.database.dao;

import java.sql.SQLException;

import com.database.model.CustomerAddressDBModel;

public class DemoDaoRunner {

	public static void main(String[] args) throws SQLException {
//		CustomerDBModel customerDBData= CustomerDao.getCustomerInfo(112772);
//		System.out.println(customerDBData);
//	System.out.println(customerDBData.getFirst_name());
//	System.out.println(customerDBData.getEmail_id());
//	System.out.println(customerDBData.getMobile_number());
//	Customer customer = new Customer("Jatin","Shharma","7045663552","","jatinvshharma@gmail.com", "");
//	Assert.assertEquals(customerDBData.getFirst_name(), customer.first_name());
//	
	
	CustomerAddressDBModel customerAddressDBModel=	CustomerAddressDao.getCustomerAddressData(113460);
		System.out.println(customerAddressDBModel);
	}

}
