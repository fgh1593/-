package com.lc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.lc.pojo.InvoiceInfo;

public interface InvoiceInfoMapper {
	
	@Select("select * from invoiceinfo where cid = #{0}")
	List<InvoiceInfo> selectByCid(Integer cid);
}
