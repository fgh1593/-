package com.lc.service;

import java.util.List;

import com.lc.pojo.Supplier;

public interface SupplierService {
	public int insertSupplier(Supplier supplier);
	
	public Supplier selectBytaxNumber(String taxNumber);
	
	public List<Supplier> selByBind(String searchSup);
	
	public List<Supplier> selAll();

	public Supplier selectBySupID(String supid);
	
	public Supplier selectByPrimaryKey(Integer id);
	
	public Integer updateSupplier(Supplier supplier);
}
