package com.lc.mapper;

import com.lc.pojo.Supplier;
import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface SupplierMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Supplier record);

    Supplier selectByPrimaryKey(Integer id);

    List<Supplier> selectAll();
    
    List<Supplier> selByBind(String searchSup);

    int updateByPrimaryKey(Supplier record);
    
    @Select("select * from supplier where suptaxnumber = #{0}")
    Supplier selectByTaxNumber(String supTaxNumber);
    
    @Select("select * from supplier where supid = #{0}")
    Supplier selectBySupID(String supID);
}