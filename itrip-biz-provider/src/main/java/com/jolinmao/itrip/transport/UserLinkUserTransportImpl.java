package com.jolinmao.itrip.transport;

import com.jolinmao.itrip.pojo.entity.UserLinkUser;
import com.jolinmao.itrip.service.UserLinkUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @auth jolinmao
 * @date 2022 07 02
 */
@RestController("userLinkUserTransport")
@RequestMapping("/userlinkuser/trans")
public class UserLinkUserTransportImpl implements UserLinkUserTransport {
	@Autowired
	private UserLinkUserService userLinkUserService;

	/**
	 * <b>查询常用联系人接口</b>
	 * @param userLinkUser
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/queryuserlinkuser")
	public List<UserLinkUser> queryUserLinkUser(@RequestBody UserLinkUser userLinkUser) throws Exception {
		return userLinkUserService.queryUserLinkUser(userLinkUser);
	}

	/**
	 * <b>新增常用联系人接口</b>
	 * @param userLinkUser
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/adduserlinkuser")
	public boolean add(@RequestBody UserLinkUser userLinkUser) throws Exception {
		return userLinkUserService.add(userLinkUser);
	}
}
