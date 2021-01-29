package com.lc.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.lc.pojo.IncomeInvoice;
import com.lc.pojo.Invoice;
import com.lc.pojo.InvoiceItem;

public interface invoiceService {
	
	
	public String saveInvoice(HttpServletRequest request);
	public String saveIncomeInvoice(HttpServletRequest request);
	public List<Invoice> getInvoiceReport(String startTime,String endTime);
	public List<IncomeInvoice> getIncomeInvoiceReport(String startTime,String endTime);
	public List<InvoiceItem> getInvoiceItems();
}
