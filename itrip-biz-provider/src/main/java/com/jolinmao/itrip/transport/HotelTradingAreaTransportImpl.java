package com.jolinmao.itrip.transport;

import com.jolinmao.itrip.pojo.entity.Hotel;
import com.jolinmao.itrip.pojo.entity.HotelTradingArea;
import com.jolinmao.itrip.service.HotelTradingAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <b>酒店商圈信息</b>
 */
@RestController("hotelTradingAreaTransport")
@RequestMapping("tradingarea/trans")
public class HotelTradingAreaTransportImpl implements HotelTradingAreaTransport {
	@Autowired
	private HotelTradingAreaService hotelTradingAreaService;

	/**
	 * <b>查询商圈</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/querytradearea")
	public List<HotelTradingArea> querytradearea(@RequestBody Hotel query) throws Exception {
		return hotelTradingAreaService.querytradearea(query);
	}
}
