package com.jolinmao.itrip.service.impl;

import com.jolinmao.itrip.dao.UserLinkUserDao;
import com.jolinmao.itrip.pojo.entity.UserLinkUser;
import com.jolinmao.itrip.service.UserLinkUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @auth jolinmao
 * @date 2022 07 02
 */
@Service("userLinkUserService")
@Transactional
public class UserLinkUserServiceImpl implements UserLinkUserService {
	@Autowired
	private UserLinkUserDao userLinkUserDao;

	/**
	 * <b>查询常用联系人接口</b>
	 * @param userLinkUser
	 * @return
	 * @throws Exception
	 */
	public List<UserLinkUser> queryUserLinkUser(UserLinkUser userLinkUser) throws Exception {
		List<UserLinkUser> userLinkUserList = userLinkUserDao.queryUserLinkUser(userLinkUser);
		if (userLinkUserList != null) {
			return userLinkUserList;
		}
		return new ArrayList<UserLinkUser>();
	}

	/**
	 * <b>新增常用联系人接口</b>
	 * @param userLinkUser
	 * @return
	 * @throws Exception
	 */
	public boolean add(UserLinkUser userLinkUser) throws Exception {
		int count = userLinkUserDao.add(userLinkUser);
		if (count > 0) {
			return true;
		}
		return false;
	}
}
