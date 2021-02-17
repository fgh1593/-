package com.lc.mapper;

import com.lc.pojo.IncomeInvoice;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface IncomeInvoiceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IncomeInvoice record);

    IncomeInvoice selectByPrimaryKey(Integer id);

    List<IncomeInvoice> selectAll();

    int updateByPrimaryKey(IncomeInvoice record);
    
    List<IncomeInvoice> selectByDate(@Param("startTime") String startTime,@Param("endTime") String endTime);

	List<IncomeInvoice> selectByNum(@Param("incomeInvoiceHead")String invoiceHeadSearch,@Param("incomeInvoiceNum") String invoiceNumSearch);
}