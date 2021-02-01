package com.lc.pojo;

import java.util.Date;

public class Invoice {
    private Integer id;

    private Integer invoiceInfoID;
    
    private Integer invoiceItemID;

    private Date time;

    private Integer taxexclude;

    private Integer tax;

    private Integer taxinclude;
    
    private InvoiceInfo info;
    
    private InvoiceItem invoiceItem;
    

    
    

	public InvoiceItem getInvoiceItem() {
		return invoiceItem;
	}

	public void setInvoiceItem(InvoiceItem invoiceItem) {
		this.invoiceItem = invoiceItem;
	}

	public Integer getInvoiceItemID() {
		return invoiceItemID;
	}

	public void setInvoiceItemID(Integer invoiceItemID) {
		this.invoiceItemID = invoiceItemID;
	}

	public InvoiceInfo getInfo() {
		return info;
	}

	public void setInfo(InvoiceInfo info) {
		this.info = info;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

	public Integer getInvoiceInfoID() {
		return invoiceInfoID;
	}

	public void setInvoiceInfoID(Integer invoiceInfoID) {
		this.invoiceInfoID = invoiceInfoID;
	}

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", invoiceInfoID=" + invoiceInfoID + ", invoiceItemID=" + invoiceItemID + ", time="
				+ time + ", taxexclude=" + taxexclude + ", tax=" + tax + ", taxinclude=" + taxinclude + ", info=" + info
				+ ", invoiceItem=" + invoiceItem + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((invoiceInfoID == null) ? 0 : invoiceInfoID.hashCode());
		result = prime * result + ((invoiceItemID == null) ? 0 : invoiceItemID.hashCode());
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
		if (invoiceInfoID == null) {
			if (other.invoiceInfoID != null)
				return false;
		} else if (!invoiceInfoID.equals(other.invoiceInfoID))
			return false;
		if (invoiceItemID == null) {
			if (other.invoiceItemID != null)
				return false;
		} else if (!invoiceItemID.equals(other.invoiceItemID))
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


	

	
	

	
    
}