package com.jolinmao.itrip.service;

import com.jolinmao.itrip.pojo.entity.Hotel;
import com.jolinmao.itrip.pojo.vo.HotelVo;
import com.jolinmao.itrip.pojo.vo.SearchHotCityVO;
import com.jolinmao.itrip.pojo.vo.SearchHotelVo;

import java.util.List;

/**
 * <b>爱旅行-区域字典信息传输层接口</b>
 * @author Jolinmao
 * @version 1.0.0
 * @since 2020 03 01
 */
public interface HotelService {

	/**
	 * <b>根据热门城市查询酒店</b>
	 * @param queryVO
	 * @return
	 * @throws Exception
	 */
	List<HotelVo> searchItripHotelListByHotCity(SearchHotCityVO queryVO) throws Exception;

	/**
	 * <b>查询酒店分页</b>
	 * @param searchHotelVo
	 * @return
	 * @throws Exception
	 */
	List<Hotel> searchItripHotelPage(SearchHotelVo searchHotelVo) throws Exception;

	/**
	 * <B>根据酒店id查询酒店特色、商圈、酒店名称</B>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<Hotel> getVideoDescById(Hotel query) throws Exception;

	/**
	 * <b>根据主键查询酒店信息</b>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Hotel getHotelById(Long id) throws Exception;
}
