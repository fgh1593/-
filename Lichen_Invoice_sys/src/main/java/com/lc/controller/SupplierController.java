package com.lc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lc.pojo.Customer;
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
	public void selectAllCus(HttpSession session) {
		List<Supplier> supList = supplierServiceImpl.selAll();
		
		session.setMaxInactiveInterval(3600);
		session.setAttribute("supList", supList);
	}
	
	@RequestMapping("/toAlterSupplier")
	public String toAlterSupplier(Integer id,HttpSession session) {
		Supplier supplier = supplierServiceImpl.selectByPrimaryKey(id);
		session.setMaxInactiveInterval(3600);
		session.setAttribute("supplier", supplier);
		return "/alterSupplier";
	}
	
	/**
	 * 修改廠商訊息
	 */
	@RequestMapping("/saveAlterSupplier")
	@ResponseBody
	public String saveAlterCustomer(Supplier supplier,HttpSession session) {
		Supplier id = supplierServiceImpl.selectBySupID(supplier.getSupid());
		//判斷廠商帶號是否已存在
		if(id!=null && !id.getId().equals(supplier.getId())) {
			return "廠商代號已存在，請更換其他代號";
		}
		//修改內容
		Integer updateSupplier = supplierServiceImpl.updateSupplier(supplier);
		if(updateSupplier==1) {
			//重製session供畫面顯示
			List<Supplier> supList = supplierServiceImpl.selAll();
			session.setMaxInactiveInterval(3600);
			session.setAttribute("supList", supList);
			return "修改成功";
		}
		
		return "尚有資料填寫不正確";
	}
}
