package com.qf.dto;

import java.io.Serializable;

public class EmailMessageDTO implements Serializable {

    private String email;
    private String url;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
