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
	
	
}
