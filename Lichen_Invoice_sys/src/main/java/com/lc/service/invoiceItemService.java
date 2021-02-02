package com.lc.service;

import java.util.List;

import com.lc.pojo.InvoiceItem;

public interface invoiceItemService {

	public List<InvoiceItem> getItemList();

	public InvoiceItem getItemByName(String name);

	public int insertItem(String name);

	public int deleteByPrimaryKey(Integer id);
}
