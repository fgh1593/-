package com.lc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lc.mapper.CustomerMapper;
import com.lc.pojo.Customer;
import com.lc.service.CustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

@Autowired
private CustomerMapper customermapper;

	@Override
	public int insertCustomer(Customer customer) {
		int i = customermapper.insert(customer);
		return i;
	}

	@Override
	public Customer selectByLichenID(String id) {
		Customer customer = customermapper.selectByLichenID(id);
		return customer;
	}

	@Override
	public List<Customer> selByBind(String searchCus) {
		
		return customermapper.selByBind(searchCus);
	}
	
	public List<Customer> selAll(){
		return customermapper.selectAll();
		
	}

	@Override
	public int updateCustomer(Customer record) {
		return customermapper.updateByPrimaryKey(record);
	}

	@Override
	public Customer selectByPrimayKey(Integer id) {
		return customermapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteCustomer(Integer id) {
		return customermapper.deleteByPrimaryKey(id);
	}
	
	
}
