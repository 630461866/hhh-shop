package com.qf.service;

import com.qf.dto.ResultBean;

public interface ISearchService {


    ResultBean searchByKeyword(String keyword);

    ResultBean addProduct(Long pid);
}
