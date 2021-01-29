package com.lc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lc.pojo.Customer;
import com.lc.pojo.InvoiceInfo;
import com.lc.service.CustomerService;

@Controller
public class CustomerController {
	@Autowired
	private CustomerService customerServiceImpl;
	


	
	@RequestMapping("/saveCustomer")
	@ResponseBody
	public String saveCustomer(@Valid Customer customer) {
		Customer id = customerServiceImpl.selectByLichenID(customer.getLichenid());
		if(id != null) {
			return "此代號已被使用";
		}
		int i = customerServiceImpl.insertCustomer(customer);
		if(i!=0) {
			return "客戶資料建立成功";
		}else {
			return "尚有資料未填寫完全";
		}
	}
	
	
	@RequestMapping("/searchCustomer")
	@ResponseBody
	public String searchCustomer(String searchCus,HttpServletRequest request) {
		
		List<Customer> cusList = customerServiceImpl.selByBind(searchCus);
		if(cusList.size()==0) {
			return "no";
		}
		request.getSession().setAttribute("cusList", cusList);
		return "yes";
		
	}
	
	
	@RequestMapping("/selectAllCus")
	@ResponseBody
	public void selectAllCus(HttpServletRequest request) {
		List<Customer> cusList = customerServiceImpl.selAll();
		request.getSession().setAttribute("cusList", cusList);
	}
	
	@RequestMapping("/selectByLcid")
	@ResponseBody
	public Object selectCustomerByLcid(String lichenid,HttpServletRequest request) {
		if(lichenid==null || lichenid=="" || lichenid==" ") {
			return "no";
		}
		Customer id = customerServiceImpl.selectByLichenID(lichenid);
		if(id==null) {
			return "no";
		}
		List<InvoiceInfo> info = id.getInvoiceInfo();
		return info;
	}
	

}
