package com.jolinmao.itrip.service.impl;

import com.jolinmao.itrip.dao.HotelTradingAreaDao;
import com.jolinmao.itrip.pojo.entity.Hotel;
import com.jolinmao.itrip.pojo.entity.HotelTradingArea;
import com.jolinmao.itrip.service.HotelTradingAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>酒店商圈信息<b/>
 * @auth jolinmao
 * @since 2022 06 29
 */
@Service("hotelTradingAreaService")
@Transactional
public class HotelTradingAreaServiceImpl implements HotelTradingAreaService {
	@Autowired
	private HotelTradingAreaDao hotelTradingAreaDao;

	/**
	 * <b>查询商圈</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public List<HotelTradingArea> querytradearea(Hotel query) throws Exception {
		List<HotelTradingArea> tradingAreaList = hotelTradingAreaDao.queryTradeArea(query);
		if (tradingAreaList != null && tradingAreaList.size() > 0) {
			return tradingAreaList;
		}
		return new ArrayList<HotelTradingArea>();
	}
}
