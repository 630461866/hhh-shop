package com.qf.entity;

public class PCA {
    private Integer id;

    private Integer pid;

    private String districtName;

    private Integer type;

    private Integer hierarchy;

    private String districtSqe;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName == null ? null : districtName.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(Integer hierarchy) {
        this.hierarchy = hierarchy;
    }

    public String getDistrictSqe() {
        return districtSqe;
    }

    public void setDistrictSqe(String districtSqe) {
        this.districtSqe = districtSqe == null ? null : districtSqe.trim();
    }
}