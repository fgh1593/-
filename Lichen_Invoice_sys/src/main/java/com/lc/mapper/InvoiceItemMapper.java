package com.lc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.lc.pojo.InvoiceItem;

public interface InvoiceItemMapper {
	@Select("select * from invoiceitem")
	List<InvoiceItem> selectAll();
}
