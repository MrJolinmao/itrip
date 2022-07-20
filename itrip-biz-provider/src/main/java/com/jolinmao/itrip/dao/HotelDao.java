package com.jolinmao.itrip.dao;

import com.jolinmao.itrip.pojo.entity.AreaDic;
import com.jolinmao.itrip.pojo.entity.Hotel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <b>爱旅行-区域字典信息传输层接口</b>
 * @author Jolinmao
 * @version 1.0.0
 * @since 2020 03 01
 */
@Repository
public interface HotelDao {

	/**
	 * <b>查询酒店分页</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<Hotel> searchItripHotelPage(AreaDic query) throws Exception;

	/**
	 * <b>根据酒店id查询酒店特色、商圈、酒店名称</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<Hotel> getVideoDescById(Hotel query) throws Exception;
}
