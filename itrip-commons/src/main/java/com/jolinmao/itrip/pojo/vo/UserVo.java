package com.jolinmao.itrip.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <b>爱旅行-用户视图对象</b>
 * @author Jolinmao
 * @version 1.0.0
 * @since 2020 03 01
 */
@ApiModel(description = "用户注册提交信息")
public class UserVo implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "用户名：手机号码/电子邮件", required = true)
	private String userCode;
	@ApiModelProperty(value = "密码", required = true)
	private String userPassword;
	@ApiModelProperty(value = "昵称", required = false)
	private String userName;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
