package com.qf.entity;

import java.util.Date;

public class TOrder {
    private Integer id;

    private String oSendtype;

    private String oPaytype;

    private Double oPaycount;

    private Date oOrderdate;

    private Integer oStatus;

    private Integer oUserid;

    private String oShperson;

    private String oShphone;

    private String oPrivince;

    private String oCity;

    private String oArea;

    private String oAddressDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getoSendtype() {
        return oSendtype;
    }

    public void setoSendtype(String oSendtype) {
        this.oSendtype = oSendtype == null ? null : oSendtype.trim();
    }

    public String getoPaytype() {
        return oPaytype;
    }

    public void setoPaytype(String oPaytype) {
        this.oPaytype = oPaytype == null ? null : oPaytype.trim();
    }

    public Double getoPaycount() {
        return oPaycount;
    }

    public void setoPaycount(Double oPaycount) {
        this.oPaycount = oPaycount;
    }

    public Date getoOrderdate() {
        return oOrderdate;
    }

    public void setoOrderdate(Date oOrderdate) {
        this.oOrderdate = oOrderdate;
    }

    public Integer getoStatus() {
        return oStatus;
    }

    public void setoStatus(Integer oStatus) {
        this.oStatus = oStatus;
    }

    public Integer getoUserid() {
        return oUserid;
    }

    public void setoUserid(Integer oUserid) {
        this.oUserid = oUserid;
    }

    public String getoShperson() {
        return oShperson;
    }

    public void setoShperson(String oShperson) {
        this.oShperson = oShperson == null ? null : oShperson.trim();
    }

    public String getoShphone() {
        return oShphone;
    }

    public void setoShphone(String oShphone) {
        this.oShphone = oShphone == null ? null : oShphone.trim();
    }

    public String getoPrivince() {
        return oPrivince;
    }

    public void setoPrivince(String oPrivince) {
        this.oPrivince = oPrivince == null ? null : oPrivince.trim();
    }

    public String getoCity() {
        return oCity;
    }

    public void setoCity(String oCity) {
        this.oCity = oCity == null ? null : oCity.trim();
    }

    public String getoArea() {
        return oArea;
    }

    public void setoArea(String oArea) {
        this.oArea = oArea == null ? null : oArea.trim();
    }

    public String getoAddressDesc() {
        return oAddressDesc;
    }

    public void setoAddressDesc(String oAddressDesc) {
        this.oAddressDesc = oAddressDesc == null ? null : oAddressDesc.trim();
    }
}