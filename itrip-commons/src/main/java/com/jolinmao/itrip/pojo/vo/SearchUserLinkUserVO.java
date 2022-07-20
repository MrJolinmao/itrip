package com.jolinmao.itrip.pojo.vo;

import java.io.Serializable;

/**
 * <b>查询常用联系人Vo</b>
 * @auth jolinmao
 * @date 2022 07 02
 */
public class SearchUserLinkUserVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String linkUserName;

	public String getLinkUserName() {
		return linkUserName;
	}

	public void setLinkUserName(String linkUserName) {
		this.linkUserName = linkUserName;
	}
}
