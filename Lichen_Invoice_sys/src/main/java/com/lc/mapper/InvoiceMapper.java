package com.lc.mapper;

import com.lc.pojo.Invoice;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface InvoiceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Invoice record);

    Invoice selectByPrimaryKey(Integer id);

    List<Invoice> selectAll();

    int updateByPrimaryKey(Invoice record);
    
    @Select("select * from invoice order by id desc limit 0,1")
    Invoice selectLastInvoice();
    
   
    List<Invoice> selectByDate(@Param("startTime") String startTime,@Param("endTime") String endTime);

	int updateNumByPrimaryKey(Integer id, String invoiceHead, String invoiceNum);

	List<Invoice> selectByNum(@Param("invoiceHead")String invoiceHeadSearch, @Param("invoiceNum")String invoiceNumSearch);
}