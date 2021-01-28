package com.lc.pojo;

import javax.validation.constraints.NotBlank;

public class Customer {
    private Integer id;
    @NotBlank
    private String lichenid;
    @NotBlank
    private String storename;
    @NotBlank
    private String receipttitle;
    @NotBlank
    private String txidnumber;

    private String storephone;

    private String shopmanager;

    private String shopmanagerphone;

    private String storeaddress;

    private String receiptaddress;

    private String depositoryaddress;

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

    public String getReceipttitle() {
        return receipttitle;
    }

    public void setReceipttitle(String receipttitle) {
        this.receipttitle = receipttitle == null ? null : receipttitle.trim();
    }

    public String getTxidnumber() {
        return txidnumber;
    }

    public void setTxidnumber(String txidnumber) {
        this.txidnumber = txidnumber == null ? null : txidnumber.trim();
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

    public String getReceiptaddress() {
        return receiptaddress;
    }

    public void setReceiptaddress(String receiptaddress) {
        this.receiptaddress = receiptaddress == null ? null : receiptaddress.trim();
    }

    public String getDepositoryaddress() {
        return depositoryaddress;
    }

    public void setDepositoryaddress(String depositoryaddress) {
        this.depositoryaddress = depositoryaddress == null ? null : depositoryaddress.trim();
    }
}