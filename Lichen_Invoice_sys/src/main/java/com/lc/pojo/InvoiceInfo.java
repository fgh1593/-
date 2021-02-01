package com.lc.pojo;

public class InvoiceInfo {
	
	private Integer id;
	private Integer cid;
	private String invoiceTitle;
	private String txIDNumber;
	private String invoiceAddress;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getInvoiceTitle() {
		return invoiceTitle;
	}
	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}
	public String getTxIDNumber() {
		return txIDNumber;
	}
	public void setTxIDNumber(String txIDNumber) {
		this.txIDNumber = txIDNumber;
	}
	public String getInvoiceAddress() {
		return invoiceAddress;
	}
	public void setInvoiceAddress(String invoiceAddress) {
		this.invoiceAddress = invoiceAddress;
	}
	@Override
	public String toString() {
		return "InvoiceInfo [id=" + id + ", cid=" + cid + ", invoiceTitle=" + invoiceTitle + ", txIDNumber="
				+ txIDNumber + ", invoiceAddress=" + invoiceAddress + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cid == null) ? 0 : cid.hashCode());
		result = prime * result + ((invoiceTitle == null) ? 0 : invoiceTitle.hashCode());
		result = prime * result + ((txIDNumber == null) ? 0 : txIDNumber.hashCode());
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
		InvoiceInfo other = (InvoiceInfo) obj;
		if (cid == null) {
			if (other.cid != null)
				return false;
		} else if (!cid.equals(other.cid))
			return false;
		if (invoiceTitle == null) {
			if (other.invoiceTitle != null)
				return false;
		} else if (!invoiceTitle.equals(other.invoiceTitle))
			return false;
		if (txIDNumber == null) {
			if (other.txIDNumber != null)
				return false;
		} else if (!txIDNumber.equals(other.txIDNumber))
			return false;
		return true;
	}
	
	
}
