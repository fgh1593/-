package com.lc.controller;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lc.pojo.Customer;
import com.lc.pojo.IncomeInvoice;
import com.lc.pojo.Invoice;
import com.lc.pojo.InvoiceInfo;
import com.lc.pojo.InvoiceItem;
import com.lc.pojo.Supplier;
import com.lc.service.CustomerService;
import com.lc.service.SupplierService;
import com.lc.service.impl.InvoiceServiceImpl;
import com.lc.utils.InvoiceNumberChecker;

@Controller
public class InvoiceController {
	
	@Autowired
	private InvoiceServiceImpl invoiceServiceImpl; 
	@Autowired
	private CustomerService customerServiceImpl;
	@Autowired
	private SupplierService supplierServiceImpl;
	@Autowired
	private InvoiceNumberChecker invoiceNumberCheker;
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
	 * @param seltitle :發票抬頭
	 * @param selitem : 發票品項
	 * @return no:客戶不存在，js會返回錯誤訊息   yes: js會將iframe頁面轉至 發票頁面 invoice.html
	 */
	@RequestMapping("/getInvoice")
	@ResponseBody
	public String getInvoice(String lichenid, Integer taxExclude,String seltitle,String selitem ,HttpServletRequest request) {
		Customer customer = customerServiceImpl.selectByLichenID(lichenid);
		
		if (customer == null) {
			return "no";
		}
		
		if(taxExclude==null) {
			return "notax";
		}
		
		InvoiceInfo info = invoiceServiceImpl.getInfo(seltitle);
		InvoiceItem item = invoiceServiceImpl.getItem(selitem);
		Invoice invoice = new Invoice();
		
		invoice.setInvoiceInfoID(info.getId());
		invoice.setInvoiceItemID(item.getId());
		invoice.setInfo(info);
		invoice.setInvoiceItem(item);
		invoice.setTaxexclude(taxExclude);
		Integer tax = (int) Math.round(taxExclude * 0.05);
		Integer taxInclude = taxExclude + tax;
		invoice.setTax(tax);
		invoice.setTaxinclude(taxInclude);
		Date date=new Date(); 
		invoice.setTime(date);
		
		//將invoice資訊儲存在session內供應thymeleaf使用
		HttpSession session = request.getSession();
		session.setAttribute("invoice", invoice);
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
	public String getIncomeInvoice(String suptaxnumber, Integer incomeTaxExclude,String time,String incomeInvoiceHead ,String incomeInvoiceNum,HttpServletRequest request) throws ParseException {
		Supplier supplier= supplierServiceImpl.selectBytaxNumber(suptaxnumber);
		if (supplier == null) {
			return "no";
		}
		if(time =="") {
			return "notime";
		}
		if(incomeTaxExclude==null) {
			return "notax";
		}
		
		
		IncomeInvoice incomeInvoice=new IncomeInvoice();
		incomeInvoice.setSupplierid(supplier.getId());
		incomeInvoice.setSupplier(supplier);
		incomeInvoice.setIncometaxexclude(incomeTaxExclude);
		Integer tax=(int) Math.round(incomeTaxExclude*0.05);
		Integer taxInclude=tax+incomeTaxExclude;
		incomeInvoice.setIncometax(tax);
		incomeInvoice.setIncometaxinclude(taxInclude);
		incomeInvoice.setIncomeInvoiceHead(incomeInvoiceHead);
		incomeInvoice.setIncomeInvoiceNum(incomeInvoiceNum);
		
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
	
	
	/**
	 * 在指定的範圍時間內，列出 進項發票 和 銷項發票的列表，並計算進項和銷項的總和金額。
	 * @param startTime : 起始範圍時間
	 * @param endTime : 結束範圍時間
	 */
	@RequestMapping("/searchReport")
	@ResponseBody
	public void searchReport(String startTime,String endTime,HttpServletRequest request) throws ParseException {
		int outTotal=0; //銷項含稅總和
		int outTaxTotal=0;//銷項稅金總和
		int outExTotal=0;//銷項未稅金總和
		int incomeTotal=0;//進項含稅金總和
		int incomeTaxTotal=0;//進項稅金總和
		int incomeExTotal=0;//進項未稅金總和
		List<IncomeInvoice> incomeInvoiceReport = invoiceServiceImpl.getIncomeInvoiceReport(startTime, endTime);
		List<Invoice> invoiceReport = invoiceServiceImpl.getInvoiceReport(startTime, endTime);
		for (IncomeInvoice incomeInvoice : incomeInvoiceReport) {
			incomeTotal+=incomeInvoice.getIncometaxinclude();
			incomeExTotal+=incomeInvoice.getIncometaxexclude();
			incomeTaxTotal+=incomeInvoice.getIncometax();
		}
		for (Invoice invoice : invoiceReport) {
			outTotal+=invoice.getTaxinclude();
			outExTotal+=invoice.getTaxexclude();
			outTaxTotal+=invoice.getTax();
		}
		request.getSession().setAttribute("incomeInvoiceReport", incomeInvoiceReport);
		request.getSession().setAttribute("invoiceReport", invoiceReport);
		request.getSession().setAttribute("outTotal",outTotal);
		request.getSession().setAttribute("outExTotal",outExTotal);
		request.getSession().setAttribute("outTaxTotal",outTaxTotal);
		request.getSession().setAttribute("incomeTotal",incomeTotal);
		request.getSession().setAttribute("incomeExTotal",incomeExTotal);
		request.getSession().setAttribute("incomeTaxTotal",incomeTaxTotal);
		request.getSession().setAttribute("startTime",startTime);
		request.getSession().setAttribute("endTime",endTime);
	}
	
	
	
	@RequestMapping("/deleteInvoice")
	@ResponseBody
	public String deleteInvoice(Integer id) {
		int i = invoiceServiceImpl.deleteInvoice(id);
		if(i==1) {
			return "刪除成功";
		}
		return "系統錯誤";
	}
	
	
	
	@RequestMapping("/deleteIncomeInvoice")
	@ResponseBody
	public String deleteIncomeInvoice(Integer id) {
		int i=invoiceServiceImpl.deleteIncomeInvoice(id);
		if(i==1) {
			return "刪除成功";
		}
		return "系統錯誤";
	}
	
	/**
	 * 進入'發票列印'功能介面時，將'發票品項'內容讀取並傳至前台
	 */
	@RequestMapping("/getInvoiceItem")
	@ResponseBody
	public List<InvoiceItem> getInvoiceItem(HttpServletRequest request) {
		List<InvoiceItem> invoiceItems = invoiceServiceImpl.getInvoiceItems();
		return invoiceItems;
	}
	
	

	
	@RequestMapping("/saveInvoiceInfo")
	@ResponseBody
	public String saveInvoiceInfo(String lichenid,String invoiceTitle,String txIDNumber,String invoiceAddress) {
		Customer customer = customerServiceImpl.selectByLichenID(lichenid);
		if(customer==null) {
			return "此客戶代碼不存在";
		}
		
		InvoiceInfo info=new InvoiceInfo();
		info.setCid(customer.getId());
		info.setInvoiceTitle(invoiceTitle);
		info.setTxIDNumber(txIDNumber);
		info.setInvoiceAddress(invoiceAddress);
		List<InvoiceInfo> list = customer.getInvoiceInfo();
		for (InvoiceInfo invoiceInfo : list) {
			if(info.equals(invoiceInfo)) {
				return "此發票資訊已存在";
			}
		}
		
		int i = invoiceServiceImpl.insertInvoiceInfo(info);
		
		if(i==1) {
			return "儲存成功";
		}
		
		return "尚有資料未填寫";
	}
	
	
	
	@RequestMapping("/deleteInvoiceInfo")
	@ResponseBody
	public String deleteInvoiceInfo(Integer id,Integer cid,HttpSession session) {
		int i = invoiceServiceImpl.deleteInvoiceInfo(id);
		//取得新的客戶訊息以供刷新頁面資訊
		Customer customer = customerServiceImpl.selectByPrimayKey(cid);
		session.setMaxInactiveInterval(3600);
		session.setAttribute("customer", customer);
		if(i==1) {
			return "刪除成功";
		}
		return "系統錯誤";
	}
	
	
	@RequestMapping("/getInvoiceNumber")
	@ResponseBody
	public String getInvoiceNumber(){
		List<String> numbers = invoiceNumberCheker.getInvoiceNum();
		if(numbers==null || numbers.size()==0) {
			invoiceNumberCheker.setInvoiceHead(null);
			return "no";
		}
		String num = numbers.get(0);
		String head=invoiceNumberCheker.getInvoiceHead();
		String str=head+"-"+num;
		return str;
	}
	
	@RequestMapping("/setInvoiceNum")
	@ResponseBody
	public String setInvoiceNum(String invoiceHead,String invoiceStartNum,String invoiceEndNum) {
		
		invoiceNumberCheker.setInvoiceNumber(invoiceHead, invoiceStartNum, invoiceEndNum);
		return "儲存成功";
	}
	
	@RequestMapping("/removeInvoiceNum")
	@ResponseBody
	public String removeInvoiceNum() {
		invoiceNumberCheker.removeInvoiceNumber();
		return "移除成功";
		
	}
}
