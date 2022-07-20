package com.jolinmao.itrip.transport;

import com.jolinmao.itrip.pojo.entity.Hotel;
import com.jolinmao.itrip.pojo.vo.HotelVo;
import com.jolinmao.itrip.pojo.vo.SearchHotCityVO;
import com.jolinmao.itrip.pojo.vo.SearchHotelVo;
import com.jolinmao.itrip.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <b>爱旅行-区域字典信息传输层接口</b>
 * @author Jolinmao
 * @version 1.0.0
 * @since 2020 03 01
 */
@RestController("hotelTransport")
@RequestMapping("/hotel/trans")
public class HotelTransportImpl implements HotelTransport {
	@Autowired
	private HotelService hotelService;


	/**
	 * <b>根据热门城市查询酒店</b>
	 * @param queryVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/searchItripHotelListByHotCity")
	public List<HotelVo> searchItripHotelListByHotCity(@RequestBody SearchHotCityVO queryVO)
			throws Exception {
		return hotelService.searchItripHotelListByHotCity(queryVO);
	}

	/**
	 * <b>查询酒店分页</b>
	 * @param searchHotelVo
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/searchItripHotelPage")
	public List<Hotel> searchItripHotelPage(@RequestBody SearchHotelVo searchHotelVo) throws Exception {
		return hotelService.searchItripHotelPage(searchHotelVo);
	}

	/**
	 * <b> 根据酒店id查询酒店特色、商圈、酒店名称</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/getVideoDescById")
	public List<Hotel> getVideoDescById(@RequestBody Hotel query) throws Exception {
		return hotelService.getVideoDescById(query);
	}

	/**
	 * <b>根据主键查询酒店信息</b>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/getHotelById")
	public Hotel getHotelById(@RequestBody Long id) throws Exception {
		return hotelService.getHotelById(id);
	}
}
