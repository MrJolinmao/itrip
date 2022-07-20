package com.jolinmao.itrip.transport;


import com.jolinmao.itrip.pojo.entity.User;
import com.jolinmao.itrip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**\
 * <b>爱旅行-用户信息传输层接口实现类</b>
 * @author Jolinmao
 * @version 1.0.0
 * @since 2020 03 01
 */
@RestController("userTransport")
@RequestMapping(value = "/user/trans")
public class UserTransportImpl implements UserTransport {
	@Autowired
	private UserService userService;

	/**
	 * <b>根据查询信息查询用户信息列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/list")
	public List<User> getUserListByQuery(@RequestBody User query) throws Exception {
		return userService.getUserListByQuery(query);
	}


	/**
	 * <b>保存用户信息</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/save")
	public boolean saveUser(@RequestBody User user) throws Exception {
		return userService.saveUser(user);
	}

	/**
	 * <b>通过userCode在Redis中查询对应的激活码</b>
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/activeCode")
	public String getActiveCodeByUserCode(@RequestParam String userCode) throws Exception {
		return userService.getActiveCodeByUserCode(userCode);
	}

	/**
	 * <b>修改用户信息</b>
	 * @param updateUser
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/update")
	public boolean updateUser(@RequestBody User updateUser) throws Exception {
		return userService.updateUser(updateUser);
	}
}
