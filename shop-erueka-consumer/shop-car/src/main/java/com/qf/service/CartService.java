package com.qf.service;


import com.qf.dto.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(value = "shop-car-service")
public interface CartService {
    @RequestMapping("/cart/add/{productId}/{count}")
    @ResponseBody
    ResultBean addProduct(@RequestParam(value = "id") String id,
                                 @PathVariable(value = "productId") Long productId,
                                 @PathVariable(value = "count") int count);



    @RequestMapping("/cart/clean")
    @ResponseBody
    ResultBean cleanCart(@RequestParam(value = "id") String id);


    @RequestMapping("/cart/update/{productId}/{count}")
    @ResponseBody
    ResultBean updateCart(
            @PathVariable(value = "productId") Long productId,
            @PathVariable(value = "count") int count,
            @RequestParam(value = "id") String id
    );



    @RequestMapping("/cart/show")
    @ResponseBody
    ResultBean showCart(@RequestParam(value = "id") String id);


    @RequestMapping("/cart/merge")
    @ResponseBody
    ResultBean merge(@RequestParam(value = "id") String id);
}
