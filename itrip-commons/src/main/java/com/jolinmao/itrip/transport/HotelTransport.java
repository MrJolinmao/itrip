package com.jolinmao.itrip.transport;

import com.jolinmao.itrip.pojo.entity.Hotel;
import com.jolinmao.itrip.pojo.vo.HotelVo;
import com.jolinmao.itrip.pojo.vo.SearchHotCityVO;
import com.jolinmao.itrip.pojo.vo.SearchHotelVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <b>爱旅行-酒店信息传输层接口</b>
 * @author Jolinmao
 * @version 1.0.0
 * @since 2020 03 01
 */
@FeignClient(name = "itrip-biz-provider")
@RequestMapping("/hotel/trans")
public interface HotelTransport {

	/**
	 * <b>根据热门城市查询酒店</b>
	 * @param queryVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/searchItripHotelListByHotCity")
	List<HotelVo> searchItripHotelListByHotCity(@RequestBody SearchHotCityVO queryVO) throws Exception;

	/**
	 * <b>查询酒店分页</b>
	 * @param searchHotelVo
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/searchItripHotelPage")
	List<Hotel> searchItripHotelPage(@RequestBody SearchHotelVo searchHotelVo) throws Exception;

	/**
	 * <b> 根据酒店id查询酒店特色、商圈、酒店名称</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/getVideoDescById")
	List<Hotel> getVideoDescById(@RequestBody Hotel query) throws Exception;

	/**
	 * <b>根据主键查询酒店信息</b>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/getHotelById")
	Hotel getHotelById(@RequestBody Long id) throws Exception;
}
