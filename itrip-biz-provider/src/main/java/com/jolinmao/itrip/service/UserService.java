package com.jolinmao.itrip.service;

import com.jolinmao.itrip.pojo.entity.User;

import java.util.List;

/**
 * <b>爱旅行-用户信息业务层接口</b>
 * @author Jolinmao
 * @version 1.0.0
 * @since 2020 03 01
 */
public interface UserService {
	/**
	 * <b>根据查询对象查询用户信息列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<User> getUserListByQuery(User query) throws Exception;


	/**
	 * <b>保存用户信息</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	boolean saveUser(User user) throws Exception;

	/**
	 * <b>通过userCode在Redis中查询对应的激活码</b>
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	String getActiveCodeByUserCode(String userCode) throws Exception;

	/**
	 * <b>修改用户信息</b>
	 * @param updateUser
	 * @return
	 * @throws Exception
	 */
	boolean updateUser(User updateUser) throws Exception;
}
