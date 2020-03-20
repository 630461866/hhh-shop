package com.qf.entity;

import java.io.Serializable;
import java.util.Date;

public class TOrder implements Serializable{
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

    private String oMessage;

    private Integer oCoupon;

    private Integer oRedpacket;

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

    public String getoMessage() {
        return oMessage;
    }

    public void setoMessage(String oMessage) {
        this.oMessage = oMessage == null ? null : oMessage.trim();
    }

    public Integer getoCoupon() {
        return oCoupon;
    }

    public void setoCoupon(Integer oCoupon) {
        this.oCoupon = oCoupon;
    }

    public Integer getoRedpacket() {
        return oRedpacket;
    }

    public void setoRedpacket(Integer oRedpacket) {
        this.oRedpacket = oRedpacket;
    }

    @Override
    public String toString() {
        return "TOrder{" +
                "id=" + id +
                ", oSendtype='" + oSendtype + '\'' +
                ", oPaytype='" + oPaytype + '\'' +
                ", oPaycount=" + oPaycount +
                ", oOrderdate=" + oOrderdate +
                ", oStatus=" + oStatus +
                ", oUserid=" + oUserid +
                ", oShperson='" + oShperson + '\'' +
                ", oShphone='" + oShphone + '\'' +
                ", oPrivince='" + oPrivince + '\'' +
                ", oCity='" + oCity + '\'' +
                ", oArea='" + oArea + '\'' +
                ", oAddressDesc='" + oAddressDesc + '\'' +
                ", oMessage='" + oMessage + '\'' +
                ", oCoupon=" + oCoupon +
                ", oRedpacket=" + oRedpacket +
                '}';
    }
}