package com.lc.mapper;

import com.lc.pojo.Customer;
import java.util.List;


public interface CustomerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Customer record);

    Customer selectByPrimaryKey(Integer id);

    List<Customer> selectAll();

    int updateByPrimaryKey(Customer record);
    
    List<Customer> selByBind(String searchCus);
    
    Customer selectByLichenID(String lichenid);
}