package com.jolinmao.itrip.util;

import java.util.Random;

/**
 * <b>激活码生成工具类</b>
 * @author Jolinmao
 * @version 1.0.0
 * @since 2020 03 01
 */
public class ActiveCodeUtil {
	public static String createActiveCode() {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			sb.append(random.nextInt(10));
		}
		return sb.toString();
	}
}
