package com.jolinmao.itrip.service;

import com.jolinmao.itrip.pojo.entity.UserLinkUser;

import java.util.List;

/**
 * @auth jolinmao
 * @date 2022 07 02
 */
public interface UserLinkUserService {

	/**
	 * <b>查询常用联系人接口</b>
	 * @param userLinkUser
	 * @return
	 * @throws Exception
	 */
	List<UserLinkUser> queryUserLinkUser(UserLinkUser userLinkUser) throws Exception;

	/**
	 * <b>新增常用联系人接口</b>
	 * @param userLinkUser
	 * @return
	 * @throws Exception
	 */
	boolean add(UserLinkUser userLinkUser) throws Exception;
}
