package com.lc.pojo;

import java.util.Date;

public class IncomeInvoice {
    private Integer id;

    private Integer supplierid;

    private Date time;

    private Integer incometaxexclude;

    private Integer incometax;

    private Integer incometaxinclude;
    
    private Supplier supplier;
    
    

    public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(Integer supplierid) {
        this.supplierid = supplierid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getIncometaxexclude() {
        return incometaxexclude;
    }

    public void setIncometaxexclude(Integer incometaxexclude) {
        this.incometaxexclude = incometaxexclude;
    }

    public Integer getIncometax() {
        return incometax;
    }

    public void setIncometax(Integer incometax) {
        this.incometax = incometax;
    }

    public Integer getIncometaxinclude() {
        return incometaxinclude;
    }

    public void setIncometaxinclude(Integer incometaxinclude) {
        this.incometaxinclude = incometaxinclude;
    }
}