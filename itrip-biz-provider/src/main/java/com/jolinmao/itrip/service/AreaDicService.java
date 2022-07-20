package com.jolinmao.itrip.service;

import com.jolinmao.itrip.pojo.entity.AreaDic;

import java.util.List;

/**
 * <b>爱旅行-区域字典信息传输层接口</b>
 * @author Jolinmao
 * @version 1.0.0
 * @since 2020 03 01
 */
public interface AreaDicService {
	/**
	 * <b>查询热门城市</b>
	 * @param query
	 * @return
	 */
	List<AreaDic> queryHotCityByQuery(AreaDic query) throws Exception;
}
