package com.lc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lc.mapper.InvoiceItemMapper;
import com.lc.pojo.InvoiceItem;
import com.lc.service.invoiceItemService;

@Service
@Transactional
public class invoiceItemServiceImpl implements invoiceItemService {
	@Autowired
	private InvoiceItemMapper invoiceItemMapper;
	
	@Override
	public List<InvoiceItem> getItemList() {
		return invoiceItemMapper.selectAll();
	}

	@Override
	public InvoiceItem getItemByName(String name) {
		return invoiceItemMapper.selectByName(name);
	}

	@Override
	public int insertItem(String name) {
		return invoiceItemMapper.insertItem(name);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return invoiceItemMapper.deleteByPrimaryKey(id);
	}
	
}
