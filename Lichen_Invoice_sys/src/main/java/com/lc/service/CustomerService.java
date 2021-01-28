package com.lc.service;

import java.util.List;

import com.lc.pojo.Customer;

public interface CustomerService {
	public int insertCustomer(Customer customer);
	
	public Customer selectByLichenID(String id);
	
	public List<Customer> selByBind(String searchCus);
	
	public List<Customer> selAll();
}
