package com.qf.dto;


import com.qf.entity.TProduct;

import java.io.Serializable;

public class TProductAddDTO extends TProduct implements Serializable {

    private String pdesc;


    public String getPdesc() {
        return pdesc;
    }

    public void setPdesc(String pdesc) {
        this.pdesc = pdesc;
    }
}
