package com.jolinmao.itrip.base.enums;

/**
 * <b>用户激活状态枚举对象</b>
 * @author Jolinmao
 * @version 1.0.0
 * @since 2020 03 01
 */
public enum UserActivatedEnum {
	USER_ACTIVATED_NO(0),
	USER_ACTIVATED_YES(1)
	;
	private int code;

	private UserActivatedEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
