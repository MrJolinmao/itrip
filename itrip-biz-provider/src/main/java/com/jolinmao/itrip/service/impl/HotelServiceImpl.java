package com.jolinmao.itrip.service.impl;

import com.github.pagehelper.PageHelper;
import com.jolinmao.itrip.dao.HotelDao;
import com.jolinmao.itrip.pojo.entity.AreaDic;
import com.jolinmao.itrip.pojo.entity.Hotel;
import com.jolinmao.itrip.pojo.vo.HotelVo;
import com.jolinmao.itrip.pojo.vo.SearchHotCityVO;
import com.jolinmao.itrip.pojo.vo.SearchHotelVo;
import com.jolinmao.itrip.service.HotelService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>爱旅行-区域字典信息传输层接口</b>
 * @author Jolinmao
 * @version 1.0.0
 * @since 2020 03 01
 */
@Service("hotelService")
@Transactional
public class HotelServiceImpl implements HotelService {
	@Autowired
	private SolrClient solrClient;
	@Autowired
	private HotelDao hotelDao;

	/**
	 * <b>根据热门城市查询酒店</b>
	 * @param queryVO
	 * @return
	 * @throws Exception
	 */
	public List<HotelVo> searchItripHotelListByHotCity(SearchHotCityVO queryVO) throws Exception {
		// 对于Spring Boot注入的SolrClient就是HttpSolrClient对象，进行强制类型转换
		HttpSolrClient httpSolrClient = (HttpSolrClient) solrClient;
		httpSolrClient.setParser(new XMLResponseParser());
		// 创建Solr的查询参数
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("cityId:" + queryVO.getCityId());
		solrQuery.setRows(queryVO.getCount());
		// 使用SolrClient进行查询，QueryResponse
		QueryResponse queryResponse = solrClient.query(solrQuery);
		// 通过使用QueryResponse提取结果
		return queryResponse.getBeans(HotelVo.class);
	}

	/**
	 * <b>查询酒店分页</b>
	 * @param searchHotelVo
	 * @return
	 * @throws Exception
	 */
	public List<Hotel> searchItripHotelPage(SearchHotelVo searchHotelVo) throws Exception {
		AreaDic query = new AreaDic();
		query.setName(searchHotelVo.getDestination());
		searchHotelVo.setPageSize(6);
		PageHelper.startPage(1, 6);
		List<Hotel> hotelList = hotelDao.searchItripHotelPage(query);
		if (hotelList != null && hotelList.size() > 0) {
			return hotelList;
		}
		return new ArrayList<Hotel>();
	}

	/**
	 * <B>根据酒店id查询酒店特色、商圈、酒店名称</B>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public List<Hotel> getVideoDescById(Hotel query) throws Exception {
		List<Hotel> hotelList = hotelDao.getVideoDescById(query);
		if (hotelList != null && hotelList.size() > 0) {
			return hotelList;
		}
		return new ArrayList<Hotel>();
	}

	/**
	 * <b>根据主键查询酒店信息</b>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Hotel getHotelById(Long id) throws Exception {
		Hotel query = new Hotel();
		query.setId(id);
		List<Hotel> hotelList = hotelDao.getVideoDescById(query);
		return hotelList.get(0);
	}
}
