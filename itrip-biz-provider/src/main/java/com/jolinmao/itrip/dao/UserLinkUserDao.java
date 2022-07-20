package com.jolinmao.itrip.dao;

import com.jolinmao.itrip.pojo.entity.UserLinkUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auth jolinmao
 * @date 2022 07 02
 */
@Repository
public interface UserLinkUserDao {

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
	int add(UserLinkUser userLinkUser) throws Exception;
}
