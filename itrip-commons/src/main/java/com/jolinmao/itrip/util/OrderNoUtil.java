package com.jolinmao.itrip.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * <b>订单编号工具类</b>
 * @auth jolinmao
 * @date 2022 07 03
 */
public class OrderNoUtil {

	public static String createOrderNo(Long hotelId, Long roomId) {
		StringBuffer sb = new StringBuffer();
		sb.append(hotelId);
		sb.append(roomId);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmSS");
		sb.append(format.format(new Date()));
		// 新增随机数
		Random random = new Random();
		sb.append(random.nextInt(10));
		String result = MD5Util.encrypt(sb.toString());
		return result.toUpperCase();
	}
}
