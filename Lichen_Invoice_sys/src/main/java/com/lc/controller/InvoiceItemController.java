package com.lc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lc.pojo.InvoiceItem;
import com.lc.service.invoiceItemService;
import com.lc.service.impl.InvoiceServiceImpl;
import com.lc.service.impl.invoiceItemServiceImpl;

@Controller
public class InvoiceItemController {
	@Autowired
	private invoiceItemService invoiceItemServiceImpl;
	
	@RequestMapping("/toInvoiceItem")
	@ResponseBody
	public void toInvoiceItem(HttpSession session) {
		List<InvoiceItem> list=	invoiceItemServiceImpl.getItemList();
		session.setMaxInactiveInterval(3600);
		session.setAttribute("itemList", list);
	}
	
	@RequestMapping("/insertItem")
	@ResponseBody
	public String insertItem(String name) {
		InvoiceItem item=invoiceItemServiceImpl.getItemByName(name);
		if(item!=null) {
			return "品項重覆";
		}
		int i=invoiceItemServiceImpl.insertItem(name);
		if(i==1) {
			return "新增成功";
		}
		return "新增失敗";
	}
	
	@RequestMapping("/deleteItem")
	@ResponseBody
	public String deleteItem(Integer id) {
		int i=invoiceItemServiceImpl.deleteByPrimaryKey(id);
		if(i==1) {
			return "刪除成功";
		}
		return "刪除失敗";
		
	}
}
