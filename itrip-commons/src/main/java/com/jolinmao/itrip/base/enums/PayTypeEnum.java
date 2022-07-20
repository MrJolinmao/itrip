package com.jolinmao.itrip.base.enums;

/**
 * @auth jolinmao
 * @date 2022 07 03
 */
public enum PayTypeEnum {
	PAY_TYPE_ALIPAY(1),
	PAY_TYPE_WECHAT(2),
	PAY_TYPE_STORE(3)
	;
	private int code;

	private PayTypeEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
