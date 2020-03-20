package com.qf.entity;

public class TCoupon {
    private Integer id;

    private String name;

    private Double discountAmount;

    private Double discountRequirement;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Double getDiscountRequirement() {
        return discountRequirement;
    }

    public void setDiscountRequirement(Double discountRequirement) {
        this.discountRequirement = discountRequirement;
    }
}