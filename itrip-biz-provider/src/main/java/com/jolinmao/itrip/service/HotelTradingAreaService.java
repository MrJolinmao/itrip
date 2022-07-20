package com.jolinmao.itrip.service;

import com.jolinmao.itrip.pojo.entity.Hotel;
import com.jolinmao.itrip.pojo.entity.HotelTradingArea;

import java.util.List;

/**
 * <b>酒店商圈信息<b/>
 */
public interface HotelTradingAreaService {

	/**
	 * <b>查询商圈</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<HotelTradingArea> querytradearea(Hotel query) throws Exception;
}
