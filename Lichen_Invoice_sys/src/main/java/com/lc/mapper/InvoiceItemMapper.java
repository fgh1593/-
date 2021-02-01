package com.lc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.lc.pojo.InvoiceItem;

public interface InvoiceItemMapper {
	@Select("select * from invoiceitem")
	List<InvoiceItem> selectAll();
	
	@Select("select * from invoiceitem where id = #{0}")
	InvoiceItem selectByPrimaryKey(Integer id);
	
	@Select("select * from invoiceitem where name=#{0}")
	InvoiceItem selectByName(String name);
}
