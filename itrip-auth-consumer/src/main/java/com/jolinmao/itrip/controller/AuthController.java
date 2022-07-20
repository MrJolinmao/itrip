package com.jolinmao.itrip.controller;

import com.jolinmao.itrip.base.controller.BaseController;
import com.jolinmao.itrip.base.enums.UserActivatedEnum;
import com.jolinmao.itrip.base.enums.UserTypeEnum;
import com.jolinmao.itrip.base.pojo.vo.ResponseDto;
import com.jolinmao.itrip.pojo.entity.User;
import com.jolinmao.itrip.pojo.vo.UserVo;
import com.jolinmao.itrip.transport.UserTransport;
import com.jolinmao.itrip.util.JWTUtil;
import com.jolinmao.itrip.util.MD5Util;
import com.jolinmao.itrip.util.RegValidationUtil;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <b>爱旅行-认证模块控制器</b>
 * @author Jolinmao
 * @version 1.0.0
 * @since 2020 03 01
 */
@Api(tags = "用户模块API")
@RestController("authController")
@RequestMapping("/auth/api")
public class AuthController extends BaseController {
	@Autowired
	private UserTransport userTransport;

	/**
	 * <b>用户名注册验证-电子邮件</b>
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "用户名注册验证")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "name", dataType = "String", required = true,value = "电子邮件")
	})
	@GetMapping(value = "/ckusr")
	public ResponseDto<Object> checkUserEmailForRegistry(String name) throws Exception {
		// 校验用户所提交的电子邮件是否有效（是否为空，以及是不是一个电子邮件格式）
		if (RegValidationUtil.validateEmail(name)) {
			// 校验通过之后，通过注册中心找到对应的生产者进行数据库校验
			// 封装查询对象
			User query = new User();
			query.setUserCode(name);
			// 进行查询
			List<User> userList = userTransport.getUserListByQuery(query);
			if (userList == null || userList.size() == 0) {
				// 此时用户注册时所填写的邮箱地址可用
				return ResponseDto.success();
			}
		}
		return ResponseDto.failure("该邮箱地址已被注册");
	}

	/**
	 * <b>使用电子邮件注册用户信息</b>
	 * @param userVo
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "使用电子邮件注册用户信息")
	@ApiResponses({
			@ApiResponse(code = 200, message = "请求成功")
	})
	@PostMapping(value = "/doregister")
	public ResponseDto<Object> registryUser(@RequestBody UserVo userVo) throws Exception {
		// 校验用户所给定信息是否有效// 校验用户所给定信息是否有效
		if (RegValidationUtil.validateEmail(userVo.getUserCode()) && RegValidationUtil.validatePassword(userVo.getUserPassword())) {
			// 进行唯一性校验
			User query = new User();
			query.setUserCode(userVo.getUserCode());
			List<User> userList = userTransport.getUserListByQuery(query);
			if (userList == null || userList.size() <= 0) {
				userVo.setUserPassword(MD5Util.encrypt(userVo.getUserPassword()));
				// 将用户注册UserVO转换成User对象
				User user = new User();
				BeanUtils.copyProperties(userVo, user);
				// 当调用该方法的时候，用户属于自主注册
				user.setUserType(UserTypeEnum.USER_TYPE_REG.getCode());
				// 将激活状态设置为未激活
				user.setActivated(UserActivatedEnum.USER_ACTIVATED_NO.getCode());
				// 使用传输层，远程调用生产者进行用户信息注册工作
				boolean flag = userTransport.saveUser(user);
				if (flag) {
					return ResponseDto.success();
				}
			}
		}
		return  ResponseDto.failure("注册失败");
	}

	/**
	 * <b>激活注册用户-邮箱</b>
	 * @param user
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@PutMapping(value = "/activate")
	public ResponseDto<Object> activeUser(@RequestParam String user, String code) throws Exception{
		// 校验用户所给定的user和code有效
		if (user != null && !"".equals(user.trim()) && code != null && !"".equals(code.trim())) {
			// 通过user在Redis中查询相应的code
			String activeCode = userTransport.getActiveCodeByUserCode(user);
			if (code.equals(activeCode)) {
				// 修改用户的激活状态
				User updateUser = new User();
				updateUser.setUserCode(user);
				updateUser.setActivated(UserActivatedEnum.USER_ACTIVATED_YES.getCode());
				updateUser.setModifyDate(new Date());
				boolean flag = userTransport.updateUser(updateUser);
				if (flag) {
					return ResponseDto.success();
				}
			}
			return ResponseDto.failure("激活码错误");
		}
		return  ResponseDto.failure("激活失败");
	}

	/**
	 * <b>使用手机号码注册用户信息</b>
	 * @param userVo
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/registerbyphone")
	public ResponseDto<Object> registryByCellphone(@RequestBody UserVo userVo) throws Exception {
		if (RegValidationUtil.validateCellphone(userVo.getUserCode())) {
			User query = new User();
			query.setUserCode(userVo.getUserCode());
			List<User> userList = userTransport.getUserListByQuery(query);
			if (userList == null || userList.size() <= 0) {
				userVo.setUserPassword(MD5Util.encrypt(userVo.getUserPassword()));
				User user = new User();
				BeanUtils.copyProperties(userVo, user);
				user.setUserType(UserTypeEnum.USER_TYPE_REG.getCode());
				user.setActivated(UserActivatedEnum.USER_ACTIVATED_NO.getCode());
				boolean flag = userTransport.saveUser(user);
				if (flag) {
					return ResponseDto.success();
				}
			}
			return ResponseDto.failure("该手机号码已被注册");
		}
		return ResponseDto.failure("注册失败");
	}

	/**
	 * <b>激活注册用户-手机号码</b>
	 * @param user
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@PutMapping(value = "/validatephone")
	public ResponseDto<Object> activeUserByCellphone(@RequestParam String user, String code) throws Exception {
		// 校验用户所给定的user和code有效
		if (user != null && !"".equals(user.trim()) && code != null && !"".equals(code.trim())) {
			// 通过user在Redis中查询相应的code
			String activeCode = userTransport.getActiveCodeByUserCode(user);
			if (code.equals(activeCode)) {
				// 修改用户的激活状态
				User updateUser = new User();
				updateUser.setUserCode(user);
				updateUser.setActivated(UserActivatedEnum.USER_ACTIVATED_YES.getCode());
				updateUser.setModifyDate(new Date());
				boolean flag = userTransport.updateUser(updateUser);
				if (flag) {
					return ResponseDto.success();
				}
			}
			return ResponseDto.failure("激活码错误");
		}
		return  ResponseDto.failure("激活失败");
	}

	/**
	 * <b>使用cellphone/email和password登陆系统</b>
	 * @param name
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/dologin")
	public ResponseDto<Object> loginUser(@RequestParam String name, String password) throws Exception {
		if (RegValidationUtil.validateCellphone(name) || RegValidationUtil.validateEmail(name) && RegValidationUtil.validatePassword(password)) {
			User query = new User();
			query.setUserCode(name);
			List<User> userList = userTransport.getUserListByQuery(query);
			if (userList != null && userList.size() > 0) {
				User user = userList.get(0);
				if (user.getUserPassword().equals(MD5Util.encrypt(password))) {
					if (user.getActivated() == UserActivatedEnum.USER_ACTIVATED_YES.getCode()) {
						// 登陆成功，按照相应的技术，生成一个Token令牌，以Cookie形式交给浏览器，
						// 每当浏览器在访问其他服务器的时候，都会携带该信息，如果需要校验该用户是否登陆，
						// 只需要校验该Token是否是按照系统规则生成的即可。
						// 在Java当中，Token技术使用了JWT（Java Web Token）来完成
						// 使用当前登陆用户的id生成Token信息
						String token = JWTUtil.createToken(user.getId());
						// 将Token随着相应交给浏览器
						response.setHeader("token", token);
						return ResponseDto.success(token);
					}
					return ResponseDto.failure("账号未激活");
				}
				return ResponseDto.failure("密码错误");
			}
			return ResponseDto.failure("该用户未注册");
		}
		return ResponseDto.failure("您输入的信息有误");
	}

	/**
	 * <b>退出系统</b>
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/logout")
	public ResponseDto<Object> logout() throws Exception {
		String Token = request.getHeader("token");
		response.setHeader("token", null);
		return ResponseDto.success();
	}
}
