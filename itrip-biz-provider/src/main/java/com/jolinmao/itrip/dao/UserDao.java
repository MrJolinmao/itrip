package com.jolinmao.itrip.dao;

import com.jolinmao.itrip.pojo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <b>爱旅行-用户信息数据持久层接口</b>
 * @author Jolinmao
 * @version 1.0.0
 * @since 2020 03 01
 */
@Repository
public interface UserDao {
	/**
	 * <b>根据查询对象查询用户信息列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<User> findListByQuery(User query) throws Exception;

	/**
	 * <b>保存用户信息</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	int saveUser(User user) throws Exception;

	/**
	 * <b>修改用户信息</b>
	 * @param updateUser
	 * @return
	 * @throws Exception
	 */
	int updateUser(User updateUser) throws Exception;
}
