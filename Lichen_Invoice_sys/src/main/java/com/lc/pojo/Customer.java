package com.lc.pojo;

import java.util.List;

import javax.validation.constraints.NotBlank;

public class Customer {
    private Integer id;
    @NotBlank
    private String lichenid;
    @NotBlank
    private String storename;
  
    private String storephone;

    private String shopmanager;

    private String shopmanagerphone;

    private String storeaddress;


    private String depositoryaddress;
    
    private List<InvoiceInfo> invoiceInfo;
    

    
    public List<InvoiceInfo> getInvoiceInfo() {
		return invoiceInfo;
	}

	public void setInvoiceInfo(List<InvoiceInfo> invoiceInfos) {
		this.invoiceInfo = invoiceInfos;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLichenid() {
        return lichenid;
    }

    public void setLichenid(String lichenid) {
        this.lichenid = lichenid == null ? null : lichenid.trim();
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename == null ? null : storename.trim();
    }


    public String getStorephone() {
        return storephone;
    }

    public void setStorephone(String storephone) {
        this.storephone = storephone == null ? null : storephone.trim();
    }

    public String getShopmanager() {
        return shopmanager;
    }

    public void setShopmanager(String shopmanager) {
        this.shopmanager = shopmanager == null ? null : shopmanager.trim();
    }

    public String getShopmanagerphone() {
        return shopmanagerphone;
    }

    public void setShopmanagerphone(String shopmanagerphone) {
        this.shopmanagerphone = shopmanagerphone == null ? null : shopmanagerphone.trim();
    }

    public String getStoreaddress() {
        return storeaddress;
    }

    public void setStoreaddress(String storeaddress) {
        this.storeaddress = storeaddress == null ? null : storeaddress.trim();
    }

   
    public String getDepositoryaddress() {
        return depositoryaddress;
    }

    public void setDepositoryaddress(String depositoryaddress) {
        this.depositoryaddress = depositoryaddress == null ? null : depositoryaddress.trim();
    }

	@Override
	public String toString() {
		return "Customer [id=" + id + ", lichenid=" + lichenid + ", storename=" + storename + ", storephone="
				+ storephone + ", shopmanager=" + shopmanager + ", shopmanagerphone=" + shopmanagerphone
				+ ", storeaddress=" + storeaddress + ", depositoryaddress=" + depositoryaddress + ", invoiceInfo="
				+ invoiceInfo + "]";
	}
    
    
}