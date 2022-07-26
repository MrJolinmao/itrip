package com.jolinmao.itrip.pojo.vo;

import java.io.Serializable;

/**
 * <b>爱旅行-搜索热门城市酒店VO</b>
 * @author Jolinmao
 * @version 1.0.0
 * @since 2020 03 01
 */
public class SearchHotCityVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer cityId;
	private Integer count;

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
