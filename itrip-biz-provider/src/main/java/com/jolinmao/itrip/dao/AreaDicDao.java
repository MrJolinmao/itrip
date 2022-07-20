package com.jolinmao.itrip.dao;

import com.jolinmao.itrip.pojo.entity.AreaDic;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <b>爱旅行-区域字典信息传输层接口</b>
 * @author Jolinmao
 * @version 1.0.0
 * @since 2020 03 01
 */
@Repository
public interface AreaDicDao {

	/**
	 * <b>查询热门城市</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<AreaDic> queryHotCityByQuery(AreaDic query) throws Exception;
}
