package com.lc.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class InvoiceNumberChecker {
	private String invoiceHead;
	private List<String> invoiceNum;

	
	public String getInvoiceHead() {
		return invoiceHead;
	}
	public void setInvoiceHead(String invoiceHead) {
		this.invoiceHead = invoiceHead;
	}
	public List<String> getInvoiceNum() {
		return invoiceNum;
	}
	public void setInvoiceNum(List<String> invoiceNum) {
		this.invoiceNum = invoiceNum;
	}
	

	
	public String setInvoiceNumber(String Head,String NumStart,String NumEnd) {
		List<String> number=new ArrayList<String>(); 
		int start = Integer.parseInt(NumStart);
		int end = Integer.parseInt(NumEnd);
		if(end-start+1>250) {
			return "一綑發票不得大於250張，請重新設定";
		}
		if(end-start<0) {
			return "設置錯誤，起始號碼應小於結束號碼";
		}
		for(int i=start;i<=end;i++) {
			String str=String.format("%08d", i);
			number.add(str);
		}
		this.invoiceNum=number;
		this.invoiceHead=Head;
		return "設定成功";
	}
	
	public void removeInvoiceNumber() {
		this.invoiceHead=null;
		this.invoiceNum=null;
	}
	
	public void toNextNumber() {
		this.invoiceNum.remove(0);
	}

	

}
