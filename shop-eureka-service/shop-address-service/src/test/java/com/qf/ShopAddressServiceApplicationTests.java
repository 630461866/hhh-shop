package com.qf;

import com.qf.dto.ResultBean;
import com.qf.entity.PCA;
import com.qf.entity.TAddress;
import com.qf.service.AddressServcie;
import com.qf.service.PcaService;
import org.aspectj.weaver.ast.Var;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ShopAddressServiceApplicationTests {
	@Autowired
	private AddressServcie addressServcie;
	@Autowired
	private PcaService pcaService;
	@Test
	void contextLoads() {
		String i = "1";
		ResultBean resultBean = addressServcie.queryAddressList(i);
		List<TAddress> list = (List<TAddress>) resultBean.getData();
		for (TAddress tAddress : list) {
			System.out.println(tAddress.getAddressDesc());
		}

		ResultBean resultBean1 = pcaService.queryProList();
		List<PCA> list1 = (List<PCA>) resultBean1.getData();
		for (PCA pca : list1) {
			System.out.println(pca.getDistrictName());
		}

		ResultBean listByProName = pcaService.getAreaListByProName("2");
		List<PCA> list2 = (List<PCA>) listByProName.getData();
		for (PCA pca : list2) {
			System.out.println(pca.getDistrictName());
		}
	}
	@Test
	void contextLoads22() {

		ResultBean resultBean = pcaService.selectByPrimaryKey("2");
		PCA pca = (PCA) resultBean.getData();
		System.out.println(pca.getDistrictName());
	}
}
