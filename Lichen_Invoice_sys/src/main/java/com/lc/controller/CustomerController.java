package com.lc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	/**
	 * 取得查詢條件的客戶列表資訊，並設置session，以供thymeleaf渲染數據
	 */
	@RequestMapping("/searchCustomer")
	@ResponseBody
	public String searchCustomer(String searchCus,HttpServletRequest request) {
		
		List<Customer> cusList = customerServiceImpl.selByBind(searchCus);
		if(cusList.size()==0) {
			return "no";
		}
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(3600);
		session.setAttribute("cusList", cusList);//儲存搜尋列表資訊
		session.setAttribute("searchCus", searchCus);//儲存搜尋資訊，此處空格代表搜尋所有，提供以後修改資訊時，刷新頁面所需數據
		return "yes";
		
	}
	
	/**
	 * 取得全不客戶列表資訊，並設置session，以供thymeleaf渲染數據
	 */
	@RequestMapping("/selectAllCus")
	@ResponseBody
	public void selectAllCus(HttpServletRequest request) {
		List<Customer> cusList = customerServiceImpl.selAll();
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(3600);
		session.setAttribute("cusList", cusList);//儲存搜尋列表資訊
		session.setAttribute("searchCus", "");//儲存搜尋資訊，此處空格代表搜尋所有，提供以後修改資訊時，刷新頁面所需數據
	}
	
	/**
	 * 判斷利成ID，獲取其發票資訊
	 * @param lichenid
	 */
	@RequestMapping("/selectByLcid")
	@ResponseBody
	public Object selectCustomerByLcid(String lichenid,HttpServletRequest request) {
		List<String> no=new ArrayList<String>();
		no.add("no");
		if(lichenid==null || lichenid=="" || lichenid==" ") {
			return no;
		}
		Customer id = customerServiceImpl.selectByLichenID(lichenid);
		if(id==null) {
			return no;
		}
		List<InvoiceInfo> info = id.getInvoiceInfo();
		return info;
	}
	
	/**
	 * 獲取表單的客戶代號，並獲取客戶，將其設置在request中，藉以進入頁面顯示詳細資料
	 */
	@RequestMapping("/toAlterCustomer")
	public String toAlterCustomer(Integer id,HttpServletRequest request) {
		Customer customer = customerServiceImpl.selectByPrimayKey(id);
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(3600);
		session.setAttribute("customer", customer);
		return "alterCustomer";
	}
	
	/**
	 * 修改客戶訊息
	 */
	@RequestMapping("/saveAlterCustomer")
	@ResponseBody
	public String saveAlterCustomer(Customer customer,HttpSession session) {
		Customer id = customerServiceImpl.selectByLichenID(customer.getLichenid());
		//判斷利成編號是否已存在
		if(id!=null && !id.getId().equals(customer.getId())) {
			return "客戶代號已存在，請更換其他代號";
		}
		//修改內容
		int updateCustomer = customerServiceImpl.updateCustomer(customer);
		if(updateCustomer==1) {
			//加入發票資訊
			customer.setInvoiceInfo(((Customer) session.getAttribute("customer")).getInvoiceInfo());
			//重製session供畫面顯示
			session.setMaxInactiveInterval(3600);
			session.setAttribute("customer", customer);

			return "修改成功";
		}
		
		return "尚有資料填寫不正確";
	}
	
	/**
	 * 設置Session供前端thymeleaf使用
	 */
	@RequestMapping("/getSession")
	@ResponseBody
	public String getSession(Integer id,HttpSession session) {
		Customer customer = customerServiceImpl.selectByPrimayKey(id);
		session.setMaxInactiveInterval(3600);
		session.setAttribute("customer", customer);
		return "成功";
	}
	
	@RequestMapping("/deleteCustomer")
	@ResponseBody
	public String deleteCustomer(Integer id) {
		int i=customerServiceImpl.deleteCustomer(id);
		if(i==1) {
			return "刪除成功";
		}
		return "刪除失敗";
	}

}
