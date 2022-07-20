package com.jolinmao.itrip.transport;

import com.jolinmao.itrip.pojo.entity.Hotel;
import com.jolinmao.itrip.pojo.entity.HotelTradingArea;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <b>酒店商圈信息</b>
 */
@FeignClient(name = "itrip-biz-provider")
@RequestMapping("tradingarea/trans")
public interface HotelTradingAreaTransport {

	/**
	 * <b>查询商圈</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/querytradearea")
	List<HotelTradingArea> querytradearea(@RequestBody Hotel query) throws Exception;
}
