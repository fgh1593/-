package com.lc.pojo;

import java.util.Date;

public class Invoice {
    private Integer id;

    private Integer cusid;

    private Date time;

    private Integer taxexclude;

    private Integer tax;

    private Integer taxinclude;
    
    private Customer customer;
    
    

    public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCusid() {
        return cusid;
    }

    public void setCusid(Integer cusid) {
        this.cusid = cusid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getTaxexclude() {
        return taxexclude;
    }

    public void setTaxexclude(Integer taxexclude) {
        this.taxexclude = taxexclude;
    }

    public Integer getTax() {
        return tax;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    public Integer getTaxinclude() {
        return taxinclude;
    }

    public void setTaxinclude(Integer taxinclude) {
        this.taxinclude = taxinclude;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cusid == null) ? 0 : cusid.hashCode());
		result = prime * result + ((tax == null) ? 0 : tax.hashCode());
		result = prime * result + ((taxexclude == null) ? 0 : taxexclude.hashCode());
		result = prime * result + ((taxinclude == null) ? 0 : taxinclude.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Invoice other = (Invoice) obj;
		if (cusid == null) {
			if (other.cusid != null)
				return false;
		} else if (!cusid.equals(other.cusid))
			return false;
		if (tax == null) {
			if (other.tax != null)
				return false;
		} else if (!tax.equals(other.tax))
			return false;
		if (taxexclude == null) {
			if (other.taxexclude != null)
				return false;
		} else if (!taxexclude.equals(other.taxexclude))
			return false;
		if (taxinclude == null) {
			if (other.taxinclude != null)
				return false;
		} else if (!taxinclude.equals(other.taxinclude))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", cusid=" + cusid + ", time=" + time + ", taxexclude=" + taxexclude + ", tax="
				+ tax + ", taxinclude=" + taxinclude + ", customer=" + customer + "]";
	}

	

	
    
}