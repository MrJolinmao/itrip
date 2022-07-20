package com.jolinmao.itrip.base.enums;

/**
 * @auth jolinmao
 * @date 2022 07 02
 */
public enum LinkIdCardTypeEnum {
	LINK_ID_CARD_TYPE_IDCARD(0),
	LINK_ID_CARD_TYPE_LICENSE(1),
	LINK_ID_CARD_TYPE_STUDENT(2),
	LINK_ID_CARD_TYPE_SOLDER(3),
	LINK_ID_CARD_TYPE_DRIVER(4),
	LINK_ID_CARD_TYPE_TRAVEL(5)
	;
	private int code;

	private LinkIdCardTypeEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
