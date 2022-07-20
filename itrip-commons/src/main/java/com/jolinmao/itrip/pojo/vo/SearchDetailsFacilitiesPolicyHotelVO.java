package com.jolinmao.itrip.pojo.vo;

import java.io.Serializable;

/**
 * @auth jolinmao
 * @date 2022 06 30
 */
public class SearchDetailsFacilitiesPolicyHotelVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String facilities;

	public String getFacilities() {
		return facilities;
	}

	public void setFacilities(String facilities) {
		this.facilities = facilities;
	}
}
