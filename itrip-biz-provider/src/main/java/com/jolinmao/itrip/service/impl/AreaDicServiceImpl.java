package com.jolinmao.itrip.service.impl;

import com.jolinmao.itrip.dao.AreaDicDao;
import com.jolinmao.itrip.pojo.entity.AreaDic;
import com.jolinmao.itrip.service.AreaDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>爱旅行-区域字典信息传输层接口</b>
 * @author Jolinmao
 * @version 1.0.0
 * @since 2020 03 01
 */
@Service("areaDicService")
@Transactional
public class AreaDicServiceImpl implements AreaDicService {
	@Autowired
	private AreaDicDao areaDicDao;

	/**
	 * <b>查询热门城市</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public List<AreaDic> queryHotCityByQuery(AreaDic query) throws Exception {
		List<AreaDic> areaDicList = areaDicDao.queryHotCityByQuery(query);
		if (areaDicList != null) {
			return areaDicList;
		}
		return new ArrayList<AreaDic>();
	}
}
