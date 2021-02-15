package com.lc.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lc.mapper.IncomeInvoiceMapper;
import com.lc.mapper.InvoiceInfoMapper;
import com.lc.mapper.InvoiceItemMapper;
import com.lc.mapper.InvoiceMapper;
import com.lc.pojo.IncomeInvoice;
import com.lc.pojo.Invoice;
import com.lc.pojo.InvoiceInfo;
import com.lc.pojo.InvoiceItem;
import com.lc.service.invoiceService;
import com.lc.utils.InvoiceNumberChecker;

@Service
@Transactional
public class InvoiceServiceImpl implements invoiceService {
	
	@Autowired
	private InvoiceMapper invoiceMapper;
	@Autowired
	private IncomeInvoiceMapper incomeInvoiceMapper;
	@Autowired
	private InvoiceItemMapper invoiceItemMapper;
	@Autowired
	private InvoiceInfoMapper invoiceInfoMapper;
	@Autowired
	private InvoiceNumberChecker invoiceNumberChecker;
	/**
	 *  將session中的發票儲存至資料庫，並判斷最後一筆發票資料有沒有重複
	 *  若重複則返回 "內容與最近一筆發票資料相同，所以沒有儲存"
	 *  若成功則返回 "儲存成功"
	 */
	@Override
	public String saveInvoice(HttpServletRequest request) {
		Invoice invoice = (Invoice) request.getSession().getAttribute("invoice");
		invoiceMapper.insert(invoice);
		invoiceNumberChecker.toNextNumber();
		return "儲存成功";
	}
	
	/**
	 * 將session中的進項發票儲存至資料庫
	 * 並將發票號碼跳號至下一號
	 */
	@Override
	public String saveIncomeInvoice(HttpServletRequest request) {
		IncomeInvoice incomeInvoice = (IncomeInvoice) request.getSession().getAttribute("incomeInvoice");
		incomeInvoiceMapper.insert(incomeInvoice);
		return "儲存成功";
	}
	
	
	@Override
	public List<Invoice> getInvoiceReport(String startTime, String endTime) {
		
		return invoiceMapper.selectByDate(startTime, endTime);
	}
	
	
	@Override
	public List<IncomeInvoice> getIncomeInvoiceReport(String startTime, String endTime) {
		return incomeInvoiceMapper.selectByDate(startTime, endTime);
	}

	@Override
	public List<InvoiceItem> getInvoiceItems() {
		
		return invoiceItemMapper.selectAll();
	}

	@Override
	public InvoiceInfo getInfo(String title) {
		
		return invoiceInfoMapper.selectBytitle(title);
	}

	@Override
	public InvoiceItem getItem(String itemName) {
		return invoiceItemMapper.selectByName(itemName);
	}

	@Override
	public int insertInvoiceInfo(InvoiceInfo info) {
		int insertInfo = invoiceInfoMapper.insertInfo(info);
		return insertInfo;
	}

	@Override
	public int deleteInvoiceInfo(int id) {
		
		return invoiceInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteInvoice(Integer id) {
		return invoiceMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteIncomeInvoice(Integer id) {
		return incomeInvoiceMapper.deleteByPrimaryKey(id);
	}
	
	

}
