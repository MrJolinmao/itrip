package com.jolinmao.itrip.service.impl;

import com.jolinmao.itrip.dao.UserDao;
import com.jolinmao.itrip.pojo.entity.User;
import com.jolinmao.itrip.service.UserService;
import com.jolinmao.itrip.util.ActiveCodeUtil;
import com.jolinmao.itrip.util.MailSenderUtil;
import com.jolinmao.itrip.util.RegValidationUtil;
import com.jolinmao.itrip.util.SmsSenderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <b>爱旅行-用户信息业务层接口实现类</b>
 * @author Jolinmao
 * @version 1.0.0
 * @since 2020 03 01
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private StringRedisTemplate redisTemplate;
	@Autowired
	private MailSenderUtil mailSenderUtil;
	@Autowired
	private SmsSenderUtil smsSenderUtil;

	/**
	 * <b>根据查询对象查询用户信息列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public List<User> getUserListByQuery(User query) throws Exception {
		return userDao.findListByQuery(query);
	}


	/**
	 * <b>保存用户信息</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean saveUser(User user) throws Exception {
		// 设定用户注册时间
		user.setCreationDate(new Date());
		int count = userDao.saveUser(user);
		if (count > 0) {
			// 产生激活码，将激活码保存到Redis中
			String activeCode = ActiveCodeUtil.createActiveCode();
			// 使用StringRedisTemplate将验证码进行保存，key为用户的email地址，value就是激活码
			redisTemplate.opsForValue().set(user.getUserCode(), activeCode);
			// 设置存储于redis中的数据存活时间
			redisTemplate.expire(user.getUserCode(), 30, TimeUnit.MINUTES);
			// 判断此时用户注册使用的是手机号码还是邮箱地址
			if (RegValidationUtil.validateEmail(user.getUserCode())) {
				// 通过发送邮件，将激活码发送给用户
				return mailSenderUtil.sendActiveCodeMail(user.getUserCode(), activeCode);
			} else if (RegValidationUtil.validateCellphone(user.getUserCode())) {
				return smsSenderUtil.sendSms(user.getUserCode(), activeCode);
			}
		}
		return false;
	}

	/**
	 * <b>通过userCode在Redis中查询对应的激活码</b>
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	public String getActiveCodeByUserCode(String userCode) throws Exception {
		String activeCode = redisTemplate.opsForValue().get(userCode);
		return activeCode;
	}

	/**
	 * <b>修改用户信息</b>
	 * @param updateUser
	 * @return
	 * @throws Exception
	 */
	public boolean updateUser(User updateUser) throws Exception {
		int count = userDao.updateUser(updateUser);
		if (count > 0) {
			return true;
		}
		return false;
	}
}
