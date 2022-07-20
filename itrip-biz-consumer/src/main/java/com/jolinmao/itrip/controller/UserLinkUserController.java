package com.jolinmao.itrip.controller;

import com.jolinmao.itrip.base.controller.BaseController;
import com.jolinmao.itrip.base.enums.LinkIdCardTypeEnum;
import com.jolinmao.itrip.base.pojo.vo.ResponseDto;
import com.jolinmao.itrip.pojo.entity.User;
import com.jolinmao.itrip.pojo.entity.UserLinkUser;
import com.jolinmao.itrip.pojo.vo.AddUserLinkUserVO;
import com.jolinmao.itrip.pojo.vo.SearchUserLinkUserVO;
import com.jolinmao.itrip.transport.UserLinkUserTransport;
import com.jolinmao.itrip.transport.UserTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import java.util.List;

/**
 * @auth jolinmao
 * @date 2022 07 02
 */
@RestController("userLinkUserController")
@RequestMapping("/biz/api/userinfo")
public class UserLinkUserController extends BaseController {
	@Autowired
	private UserLinkUserTransport userLinkUserTransport;
	@Autowired
	private UserTransport userTransport;

	/**
	 * <b>查询常用联系人接口</b>
	 * @param userLinkUserVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/queryuserlinkuser")
	public ResponseDto<Object> queryUserLinkUser(@RequestBody SearchUserLinkUserVO userLinkUserVO) throws Exception {
		UserLinkUser userLinkUser = new UserLinkUser();
		String userCode = "";
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if ("user".equals(cookie.getName())) {
				userCode = cookie.getValue();
			}
		}
		userLinkUser.setUserCode(userCode);
		User query = new User();
		query.setUserCode(userCode);
		userLinkUser.setUserId(userTransport.getUserListByQuery(query).get(0).getId().intValue());
		userLinkUser.setLinkUserName(userLinkUserVO.getLinkUserName());
		List<UserLinkUser> userLinkUserList = userLinkUserTransport.queryUserLinkUser(userLinkUser);
		return ResponseDto.success(userLinkUserList);
	}

	/**
	 * <b>新增常用联系人接口</b>
	 * @param addUserLinkUserVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/adduserlinkuser")
	public ResponseDto<Object> addUserLinkUser(@RequestBody AddUserLinkUserVO addUserLinkUserVO) throws Exception {
		UserLinkUser userLinkUser = new UserLinkUser();
		userLinkUser.setLinkUserName(addUserLinkUserVO.getLinkUserName());
		userLinkUser.setLinkIdCard(addUserLinkUserVO.getLinkIdCard());
		userLinkUser.setLinkPhone(addUserLinkUserVO.getLinkPhone());

		String userCode = "";
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if ("user".equals(cookie.getName())) {
				userCode = cookie.getValue();
			}
		}
		User query = new User();
		query.setUserCode(userCode);

		userLinkUser.setUserId(userTransport.getUserListByQuery(query).get(0).getId().intValue());
		userLinkUser.setLinkIdCardType(LinkIdCardTypeEnum.LINK_ID_CARD_TYPE_IDCARD.getCode());
		boolean flag = userLinkUserTransport.add(userLinkUser);
		if (flag) {
			return ResponseDto.success();
		}
		return ResponseDto.failure("添加失败");
	}
}
