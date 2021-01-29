package com.lc.controller;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lc.mapper.CustomerMapper;
import com.lc.mapper.SupplierMapper;
import com.lc.pojo.Customer;
import com.lc.pojo.IncomeInvoice;
import com.lc.pojo.Invoice;
import com.lc.pojo.InvoiceItem;
import com.lc.pojo.Supplier;
import com.lc.service.impl.InvoiceServiceImpl;

@Controller
public class InvoiceController {
	
	@Autowired
	private InvoiceServiceImpl invoiceServiceImpl; 
		
	@Autowired
	private CustomerMapper customerMapper;
	

	
	@Autowired
	private SupplierMapper supplierMapper;
	/**
	 * 將頁面導入template資源路徑
	 * Customer 用來校驗前端頁面資料是否有空錯誤
	 * @param page
	 * @return
	 */
	@RequestMapping("{page}")
	public String toPage(@PathVariable String page) {
		return page;
	}
	
	/**
	 * 將輸入的資訊轉換為發票顯示資訊
	 * @param lichenid :客戶的代號
	 * @param taxExclude :發票未稅金額
	 * @return no:客戶不存在，js會返回錯誤訊息   yes: js會將iframe頁面轉至 發票頁面 invoice.html
	 */
	@RequestMapping("/getInvoice")
	@ResponseBody
	public String getInvoice(String lichenid, Integer taxExclude,String selcus,String selitem ,HttpServletRequest request) {
		Customer customer = customerMapper.selectByLichenID(lichenid);
		
		if (customer == null) {
			return "no";
		}
		
		Invoice invoice = new Invoice();
		invoice.setCusid(customer.getId());
		invoice.setCustomer(customer);
		invoice.setTaxexclude(taxExclude);
		Integer tax = (int) Math.round(taxExclude * 0.05);
		Integer taxInclude = taxExclude + tax;
		invoice.setTax(tax);
		invoice.setTaxinclude(taxInclude);
		Date date=new Date(); 
		invoice.setTime(date);
		
		//將invoice資訊儲存在session內供應thymeleaf使用
		request.getSession().setAttribute("invoice", invoice);
		return "yes";

	}
	
	

	/**
	 * 將資料轉換成Invoice 儲存在Session中，並提供給thymeleaf渲染
	 * @param suptaxnumber :廠商統編
	 * @param incomeTaxExclude : 發票未稅金額
	 * @param time :發票時間
	 * @return
	 */
	@RequestMapping("/getIncomeInvoice")
	@ResponseBody
	public String getIncomeInvoice(String suptaxnumber, Integer incomeTaxExclude,String time, HttpServletRequest request) throws ParseException {
		Supplier supplier= supplierMapper.selectByTaxNumber(suptaxnumber);
		if (supplier == null) {
			return "no";
		}
		if(time =="") {
			return "notime";
		}
		if(incomeTaxExclude==null) {
			return "notax";
		}
		System.out.println(incomeTaxExclude);
		System.out.println(time);
		
		IncomeInvoice incomeInvoice=new IncomeInvoice();
		incomeInvoice.setSupplierid(supplier.getId());
		incomeInvoice.setSupplier(supplier);
		incomeInvoice.setIncometaxexclude(incomeTaxExclude);
		Integer tax=(int) Math.round(incomeTaxExclude*0.05);
		Integer taxInclude=tax+incomeTaxExclude;
		incomeInvoice.setIncometax(tax);
		incomeInvoice.setIncometaxinclude(taxInclude);
		
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Date date = df.parse(time);
		incomeInvoice.setTime(date);
		
		
		//將invoice資訊儲存在session內供應thymeleaf使用
		request.getSession().setAttribute("incomeInvoice", incomeInvoice);
		return "yes";

	}
	
	/**
	 * 將session中的發票儲存至資料庫，返回字串給JS顯現
	 */
	@RequestMapping("/saveInvoice")
	@ResponseBody
	public String saveInvoice(HttpServletRequest request) {
		return invoiceServiceImpl.saveInvoice(request);

	}
	
	/**
	 * 將session中的進項發票儲存至資料庫，返回成功字串給JS顯現
	 */
	@RequestMapping("/saveIncomeInvoice")
	@ResponseBody
	public String saveIncomeInvoice(HttpServletRequest request) {
		return invoiceServiceImpl.saveIncomeInvoice(request);

	}
	
	@RequestMapping("/searchReport")
	@ResponseBody
	public void searchReport(String startTime,String endTime,HttpServletRequest request) throws ParseException {
		int outTotal=0;
		int incomeTotal=0;
		List<IncomeInvoice> incomeInvoiceReport = invoiceServiceImpl.getIncomeInvoiceReport(startTime, endTime);
		List<Invoice> invoiceReport = invoiceServiceImpl.getInvoiceReport(startTime, endTime);
		for (IncomeInvoice incomeInvoice : incomeInvoiceReport) {
			incomeTotal+=incomeInvoice.getIncometaxinclude();
		}
		for (Invoice invoice : invoiceReport) {
			outTotal+=invoice.getTaxinclude();
		}
		request.getSession().setAttribute("incomeInvoiceReport", incomeInvoiceReport);
		request.getSession().setAttribute("invoiceReport", invoiceReport);
		request.getSession().setAttribute("outTotal",outTotal);
		request.getSession().setAttribute("incomeTotal",incomeTotal);
	}
	
	@RequestMapping("/toInvoice")
	@ResponseBody
	public List<InvoiceItem> toInvoice(HttpServletRequest request) {
		List<InvoiceItem> invoiceItems = invoiceServiceImpl.getInvoiceItems();
		return invoiceItems;
	}
}
