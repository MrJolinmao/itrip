package com.jolinmao.itrip.dao;

import com.jolinmao.itrip.pojo.entity.Hotel;
import com.jolinmao.itrip.pojo.entity.HotelTradingArea;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <b>酒店商圈信息<b/>
 * @auth jolinmao
 * @date 2022 06 29
 */
@Repository
public interface HotelTradingAreaDao {

	List<HotelTradingArea> queryTradeArea(Hotel query) throws Exception;
}
