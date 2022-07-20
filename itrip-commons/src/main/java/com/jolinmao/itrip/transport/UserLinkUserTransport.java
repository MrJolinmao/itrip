package com.jolinmao.itrip.transport;

import com.jolinmao.itrip.pojo.entity.UserLinkUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @auth jolinmao
 * @date 2022 07 02
 */
@FeignClient(name = "itrip-biz-provider")
@RequestMapping("/userlinkuser/trans")
public interface UserLinkUserTransport {


	/**
	 * <b>查询常用联系人接口</b>
	 * @param userLinkUser
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/queryuserlinkuser")
	List<UserLinkUser> queryUserLinkUser(@RequestBody UserLinkUser userLinkUser) throws Exception;

	/**
	 * <b>新增常用联系人接口</b>
	 * @param userLinkUser
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/adduserlinkuser")
	boolean add(@RequestBody UserLinkUser userLinkUser) throws Exception;
}
