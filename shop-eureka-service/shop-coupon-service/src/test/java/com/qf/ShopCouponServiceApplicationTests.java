package com.qf;

import com.qf.entity.TCoupon;
import com.qf.entity.TUserCoupon;
import com.qf.mapper.TCouponMapper;
import com.qf.mapper.TUserCouponMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ShopCouponServiceApplicationTests {
	@Autowired
	private TCouponMapper couponMapper;
	@Autowired
	private TUserCouponMapper tUserCouponMapper;
	@Test
	void contextLoads() {
		List<TCoupon> tCoupons = couponMapper.queryCouponByUserId(1);
		for (TCoupon tCoupon : tCoupons) {
			System.out.println(tCoupon.getId());
		}

		List<TUserCoupon> tUserCouponList = tUserCouponMapper.queryUserCouponByUserId(1);
		for (TUserCoupon tUserCoupon : tUserCouponList) {
			System.out.println(tUserCoupon.getRedpacketSurplus());
		}
	}

}
