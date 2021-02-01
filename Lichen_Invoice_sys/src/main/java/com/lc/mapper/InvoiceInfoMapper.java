package com.lc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.lc.pojo.InvoiceInfo;

public interface InvoiceInfoMapper {
	
	@Select("select * from invoiceinfo where cid = #{0}")
	List<InvoiceInfo> selectByCid(Integer cid);
	
	@Select("select * from invoiceinfo where invoiceTitle =#{0}")
	InvoiceInfo selectBytitle(String title);
	
	@Select("select * from invoiceinfo where id=#{0}")
	InvoiceInfo selectByPrimaryKey(Integer id);
	
	@Insert("insert into invoiceinfo(id,cid,invoiceTitle,txIDNumber,invoiceAddress) values(#{id},#{cid},#{invoiceTitle},#{txIDNumber},#{invoiceAddress}) ")
	int insertInfo(InvoiceInfo info);
	
	@Delete("delete from invoiceinfo where id = #{0}")
	int deleteByPrimaryKey(Integer id);
}
