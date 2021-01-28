package com.lc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lc.mapper.SupplierMapper;
import com.lc.pojo.Supplier;
import com.lc.service.SupplierService;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	private SupplierMapper supplierMapper;

	@Override
	public int insertSupplier(Supplier supplier) {
		int i = supplierMapper.insert(supplier);
		return i;
	}

	@Override
	public Supplier selectBytaxNumber(String taxNumber) {
		Supplier supplier = supplierMapper.selectByTaxNumber(taxNumber);
		return supplier;
	}

	@Override
	public List<Supplier> selByBind(String searchSup) {

		return supplierMapper.selByBind(searchSup);
	}

	public List<Supplier> selAll() {
		return supplierMapper.selectAll();
	}

	@Override
	public Supplier selectBySupID(String supid) {
		return supplierMapper.selectBySupID(supid);
	}

}
