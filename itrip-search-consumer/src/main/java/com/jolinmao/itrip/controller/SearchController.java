package com.jolinmao.itrip.controller;

import com.jolinmao.itrip.base.controller.BaseController;
import com.jolinmao.itrip.base.pojo.vo.ResponseDto;
import com.jolinmao.itrip.pojo.entity.Hotel;
import com.jolinmao.itrip.pojo.vo.HotelVo;
import com.jolinmao.itrip.pojo.vo.SearchHotCityVO;
import com.jolinmao.itrip.pojo.vo.SearchHotelVo;
import com.jolinmao.itrip.transport.HotelTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <b>爱旅行-</b>
 * @auth jolinmao
 * @date 2022 06 28
 * @version 1.0.0
 */
@RestController("searchController")
@RequestMapping("/search/api")
public class SearchController extends BaseController {
	@Autowired
	private HotelTransport hotelTransport;

	/**
	 * <b>根据热门城市查询酒店</b>
	 * @param queryVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/hotellist/searchItripHotelListByHotCity")
	public ResponseDto<Object> searchItripHotelListByHotCity(@RequestBody SearchHotCityVO queryVO)
			throws Exception {
		List<HotelVo> hotelVoList = hotelTransport.searchItripHotelListByHotCity(queryVO);
 		return ResponseDto.success(hotelVoList);
	}

	/**
	 * <b>查询酒店分页</b>
	 * @param searchHotelVo
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "hotellist/searchItripHotelPage")
	public ResponseDto<Object> searchItripHotelPage(@RequestBody SearchHotelVo searchHotelVo) throws Exception {
		List<Hotel> hotelList = hotelTransport.searchItripHotelPage(searchHotelVo);
		return ResponseDto.success(hotelList);
	}
}
