package com.qf;

import com.alibaba.fastjson.JSON;
import com.qf.dto.ResultBean;
import com.qf.entity.TAddress;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootTest
class ShopOrderApplicationTests {
	@Autowired
	private RestTemplate restTemplate;
	@Test
	void contextLoads() {
		String url = "http://localhost:7778/address/queryaddress/"+37;
		ResultBean resultBean = restTemplate.getForObject(url, ResultBean.class);
		String AddressJson = (String) resultBean.getData();
		List<TAddress> addressList = JSON.parseArray(AddressJson,TAddress.class);
		for (TAddress tAddress : addressList) {
			System.out.println(tAddress.getAddressDesc());
		}
	}

}
