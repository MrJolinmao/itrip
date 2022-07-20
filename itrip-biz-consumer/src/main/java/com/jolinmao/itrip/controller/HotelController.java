package com.jolinmao.itrip.controller;

import com.jolinmao.itrip.base.controller.BaseController;
import com.jolinmao.itrip.base.enums.AreaDicIsHotEnum;
import com.jolinmao.itrip.base.pojo.vo.ResponseDto;
import com.jolinmao.itrip.pojo.entity.AreaDic;
import com.jolinmao.itrip.pojo.entity.Hotel;
import com.jolinmao.itrip.pojo.entity.HotelTradingArea;
import com.jolinmao.itrip.pojo.entity.LabelDic;
import com.jolinmao.itrip.pojo.vo.SearchDetailsFacilitiesPolicyHotelVO;
import com.jolinmao.itrip.pojo.vo.SearchDetailsHotelVO;
import com.jolinmao.itrip.transport.AreaDicTransport;
import com.jolinmao.itrip.transport.HotelTradingAreaTransport;
import com.jolinmao.itrip.transport.HotelTransport;
import com.jolinmao.itrip.transport.LabelDicTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>爱旅行-认证模块控制器</b>
 * @author Jolinmao
 * @version 1.0.0
 * @since 2020 03 01
 */
@RestController("hotelController")
@RequestMapping("/biz/api/hotel/")
public class HotelController extends BaseController {
	@Autowired
	private AreaDicTransport areaDicTransport;
	@Autowired
	private LabelDicTransport labelDicTransport;
	@Autowired
	private HotelTradingAreaTransport hotelTradingAreaTransport;
	@Autowired
	private HotelTransport hotelTransport;


	/**
	 * <b>查询热门城市</b>
	 * @param isChina
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/queryhotcity/{isChina}")
	public ResponseDto<Object> queryHotCityByQuery(@PathVariable("isChina") Integer isChina) throws Exception {
		AreaDic query = new AreaDic();
		query.setIsChina(isChina);
		query.setIsHot(AreaDicIsHotEnum.AREA_DIC_IS_HOT_ENUM_YES.getCode());
		List<AreaDic> areaDicList = areaDicTransport.queryHotCityByQuery(query);
		return ResponseDto.success(areaDicList);
	}

	/**
	 * <b>查询酒店特色列表</b>
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/queryhotelfeature")
	public ResponseDto<Object> queryhotelfeature() throws Exception {
		LabelDic labelDic = new LabelDic();
		labelDic.setParentId(16L);
		List<LabelDic> labelDicList = labelDicTransport.queryhotelfeature(labelDic);
		return ResponseDto.success(labelDicList);
	}

	/**
	 * <b>查询商圈</b>
	 * @param cityId
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/querytradearea/{cityId}")
	public ResponseDto<Object> querytradearea(@PathVariable("cityId") long cityId) throws Exception {
		Hotel query = new Hotel();
		query.setCityId(cityId);
		List<HotelTradingArea> tradingAreaList = hotelTradingAreaTransport.querytradearea(query);
		return ResponseDto.success(tradingAreaList);
	}

	/**
	 * <b> 根据酒店id查询酒店特色、商圈、酒店名称</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/getvideodesc/{hotelId}")
	public ResponseDto<Object> getVideoDescById(@PathVariable("hotelId") Long hotelId) throws Exception {
		Hotel query = new Hotel();
		query.setId(hotelId);
		List<Hotel> hotelList = hotelTransport.getVideoDescById(query);
		return ResponseDto.success(hotelList);
	}

	/**
	 * 根据酒店id查询酒店特色和介绍
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/queryhoteldetails/{id}")
	public ResponseDto<Object> queryHotelDetails(@PathVariable("id") Long id) throws Exception {
		List<SearchDetailsHotelVO> resultList = new ArrayList<SearchDetailsHotelVO>();
		Hotel query = new Hotel();
		query.setId(id);
		List<Hotel> hotelList = hotelTransport.getVideoDescById(query);
		Hotel hotel = hotelList.get(0);
		resultList.add(new SearchDetailsHotelVO("酒店介绍", hotel.getDetails()));
		LabelDic queryLabelDic = new LabelDic();
		queryLabelDic.setHotelId(id);
		List<LabelDic> labelDicList = labelDicTransport.queryhotelfeature(queryLabelDic);
		for (LabelDic labelDic : labelDicList) {
			resultList.add(new SearchDetailsHotelVO(labelDic.getName(), labelDic.getDescription()));
		}
		return  ResponseDto.success(resultList);
	}

	/**
	 * <b>根据酒店id查询酒店设施</b>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/queryhotelfacilities/{id}")
	public ResponseDto<Object> queryHotelFacilities(@PathVariable("id") Long id) throws Exception {
		Hotel hotel = new Hotel();
		hotel.setId(id);
		List<Hotel> hotelList = hotelTransport.getVideoDescById(hotel);
		return ResponseDto.success(hotelList.get(0).getFacilities());
	}

	/**
	 * <b>根据酒店id查询酒店政策</b>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/queryhotelpolicy/{id}")
	public ResponseDto<Object> queryHotelPolicy(@PathVariable("id") Long id) throws Exception {
		Hotel hotel = new Hotel();
		hotel.setId(id);
		List<Hotel> hotelList = hotelTransport.getVideoDescById(hotel);
		return ResponseDto.success(hotelList.get(0).getHotelPolicy());
	}
}
