package com.qf.entity;

public class TAddress {
    private Integer id;

    private String shouhuoren;

    private String phone;

    private String province;

    private String city;

    private String area;

    private String addressDesc;

    private Integer userid;

    private String isdefault;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShouhuoren() {
        return shouhuoren;
    }

    public void setShouhuoren(String shouhuoren) {
        this.shouhuoren = shouhuoren == null ? null : shouhuoren.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getAddressDesc() {
        return addressDesc;
    }

    public void setAddressDesc(String addressDesc) {
        this.addressDesc = addressDesc == null ? null : addressDesc.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(String isdefault) {
        this.isdefault = isdefault == null ? null : isdefault.trim();
    }
}