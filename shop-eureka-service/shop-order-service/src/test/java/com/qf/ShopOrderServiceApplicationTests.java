package com.qf;

import com.qf.entity.TOrder;
import com.qf.entity.TOrderDetail;
import com.qf.mapper.TOrderDetailMapper;
import com.qf.mapper.TOrderMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShopOrderServiceApplicationTests {

	@Autowired
	private TOrderMapper tOrderMapper;

	@Autowired
	private TOrderDetailMapper tOrderDetailMapper;
	@Test
	void contextLoads() {
		TOrder tOrder = tOrderMapper.selectByPrimaryKey(2020551983);
		System.out.println(tOrder.getoAddressDesc());
	}
	@Test
	void contextLoads2() {
		TOrderDetail tOrderDetail = tOrderDetailMapper.selectByPrimaryKey(49);
		System.out.println(tOrderDetail.getGoodsDescription());
	}
}
