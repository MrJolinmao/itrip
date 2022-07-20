package com.jolinmao.itrip.base.enums;

/**
 * <b>区域字典信息枚举对象</b>
 * @author Jolinmao
 * @version 1.0.0
 * @since 2020 03 01
 */
public enum AreaDicIsHotEnum {
	AREA_DIC_IS_HOT_ENUM_YES(1),
	AREA_DIC_IS_HOT_ENUM_NO(0)
	;
	private int code;

	 private AreaDicIsHotEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
