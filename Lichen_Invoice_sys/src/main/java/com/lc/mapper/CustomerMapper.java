package com.lc.mapper;

import com.lc.pojo.Customer;
import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface CustomerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Customer record);

    Customer selectByPrimaryKey(Integer id);

    List<Customer> selectAll();

    int updateByPrimaryKey(Customer record);
    
    @Select("select * from customer where lichenid = #{0}")
    Customer selectByLichenID(String lichenid);
    
    List<Customer> selByBind(String searchCus);
}