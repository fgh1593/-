package com.lc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.lc.pojo.InvoiceItem;

public interface InvoiceItemMapper {
	@Select("select * from invoiceitem order by id")
	List<InvoiceItem> selectAll();
	
	@Select("select * from invoiceitem where id = #{0}")
	InvoiceItem selectByPrimaryKey(Integer id);
	
	@Select("select * from invoiceitem where name=#{0}")
	InvoiceItem selectByName(String name);
	
	@Insert("insert into invoiceitem(name) values(#{0})")
	int insertItem(String name);
	
	@Delete("delete from invoiceitem where id=#{0}")
	int deleteByPrimaryKey(Integer id);
}
