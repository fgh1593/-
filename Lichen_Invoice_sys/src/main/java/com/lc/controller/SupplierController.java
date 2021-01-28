package com.lc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lc.pojo.Supplier;
import com.lc.service.SupplierService;

@Controller
public class SupplierController {
	@Autowired
	private SupplierService supplierServiceImpl;
	


	
	@RequestMapping("/saveSupplier")
	@ResponseBody
	public String saveCustomer(@Valid Supplier supplier) {
		Supplier taxNumber = supplierServiceImpl.selectBytaxNumber(supplier.getSuptaxnumber());
		Supplier supID = supplierServiceImpl.selectBySupID(supplier.getSupid());
		if(taxNumber != null) {
			return "此統一編號已被使用";
		}
		if(supID != null) {
			return "此廠商編號已被使用";
		}
		int i = supplierServiceImpl.insertSupplier(supplier);
		if(i!=0) {
			return "廠商資料建立成功";
		}else {
			return "尚有資料未填寫完全";
		}
	}
	
	
	@RequestMapping("/searchSupplier")
	@ResponseBody
	public String searchCustomer(String searchSup,HttpServletRequest request) {
		
		List<Supplier> supList = supplierServiceImpl.selByBind(searchSup);
		if(supList.size()==0) {
			return "no";
		}
		request.getSession().setAttribute("supList", supList);
		return "yes";
		
	}
	
	
	@RequestMapping("/selectAllSup")
	@ResponseBody
	public void selectAllCus(HttpServletRequest request) {
		List<Supplier> supList = supplierServiceImpl.selAll();
		request.getSession().setAttribute("supList", supList);
	}
}
