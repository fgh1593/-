package com.lc.pojo;

import javax.validation.constraints.NotBlank;

public class Supplier {
    private Integer id;
    @NotBlank
    private String supid;
    @NotBlank
    private String supname;

    private String supphone;
    @NotBlank
    private String suptitle;
    @NotBlank
    private String suptaxnumber;

    private String supaddress;
    
    
    
    public String getSupid() {
		return supid;
	}

	public void setSupid(String supid) {
		this.supid = supid;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSupname() {
        return supname;
    }

    public void setSupname(String supname) {
        this.supname = supname == null ? null : supname.trim();
    }

    public String getSupphone() {
        return supphone;
    }

    public void setSupphone(String supphone) {
        this.supphone = supphone == null ? null : supphone.trim();
    }

    public String getSuptitle() {
        return suptitle;
    }

    public void setSuptitle(String suptitle) {
        this.suptitle = suptitle == null ? null : suptitle.trim();
    }

    public String getSuptaxnumber() {
        return suptaxnumber;
    }

    public void setSuptaxnumber(String suptaxnumber) {
        this.suptaxnumber = suptaxnumber == null ? null : suptaxnumber.trim();
    }

    public String getSupaddress() {
        return supaddress;
    }

    public void setSupaddress(String supaddress) {
        this.supaddress = supaddress == null ? null : supaddress.trim();
    }
}