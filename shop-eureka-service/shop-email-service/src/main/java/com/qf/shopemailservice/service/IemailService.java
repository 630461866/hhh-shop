package com.qf.shopemailservice.service;


import com.qf.dto.ResultBean;

public interface IemailService {


    ResultBean registByEmail( String email,String password);

}
