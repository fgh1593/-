package com.lc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lc.mapper.CustomerMapper;
import com.lc.pojo.Customer;


@SpringBootTest
class LichenInvoiceSysApplicationTests {
	
	@Autowired
	CustomerMapper customerMapper;
	
	@Test
	void contextLoads() {
		Customer id = customerMapper.selectByLichenID("ㄅ999");
		
		System.out.println(id.getInvoiceInfo());
	}

}
