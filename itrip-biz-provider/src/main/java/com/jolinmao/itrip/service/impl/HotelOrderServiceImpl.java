package com.jolinmao.itrip.service.impl;

import com.jolinmao.itrip.dao.HotelOrderDao;
import com.jolinmao.itrip.pojo.entity.HotelOrder;
import com.jolinmao.itrip.pojo.vo.Page;
import com.jolinmao.itrip.pojo.vo.SearchOrderVO;
import com.jolinmao.itrip.service.HotelOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auth jolinmao
 * @date 2022 07 02
 */
@Service("hotelOrderService")
@Transactional
public class HotelOrderServiceImpl implements HotelOrderService {
	@Autowired
	private HotelOrderDao hotelOrderDao;

	/**
	 * <b>查询订单信息</b>
	 * @param queryOrder
	 * @return
	 * @throws Exception
	 */
	public List<HotelOrder> getHotelOrderByQuery(HotelOrder queryOrder) throws Exception {
		List<HotelOrder> hotelOrderList = hotelOrderDao.getHotelOrderByQuery(queryOrder);
		if (hotelOrderList != null && hotelOrderList.size() > 0) {
			return hotelOrderList;
		}
		return new ArrayList<HotelOrder>();
	}

	/**
	 * <b>根据订单编号查询订单信息</b>
	 * @param queryOrder
	 * @return
	 * @throws Exception
	 */
	public HotelOrder getHotelOrderByOrderNo(HotelOrder queryOrder) throws Exception {
		HotelOrder hotelOrder = hotelOrderDao.getHotelOrderByQuery(queryOrder).get(0);
		return hotelOrder;
	}

	/**
	 * <b>根据订单id查询订单信息</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public HotelOrder getHotelOrderByOrderId(String orderId)throws Exception {
		HotelOrder queryOrder = new HotelOrder();
		queryOrder.setId(Long.parseLong(orderId));
		HotelOrder hotelOrder = hotelOrderDao.getHotelOrderByQuery(queryOrder).get(0);
		return hotelOrder;
	}

	/**
	 * <b>查询个人订单列表，并分页显示</b>
	 * @param searchOrderVO
	 * @return
	 */
	public Page<HotelOrder> getPersonalOrderList(SearchOrderVO searchOrderVO) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("endDate", searchOrderVO.getEndDate());
		queryMap.put("linkUserName", searchOrderVO.getLinkUserName());
		queryMap.put("orderNo", searchOrderVO.getOrderNo());
		queryMap.put("orderStatus", searchOrderVO.getOrderStatus() == -1 ? null : searchOrderVO.getOrderStatus());
		queryMap.put("orderType", searchOrderVO.getOrderType() == -1 ? null : searchOrderVO.getOrderType());
		queryMap.put("pageNo", searchOrderVO.getPageNo());
		queryMap.put("pageSize", searchOrderVO.getPageSize());
		queryMap.put("startDate", searchOrderVO.getStartDate());
		List<HotelOrder> orderList = hotelOrderDao.getPersonalOrderList(queryMap);
		Page<HotelOrder> page = new Page<>(searchOrderVO.getPageNo(), orderList.size(), searchOrderVO.getPageSize());
		page.setRows(orderList);
		return page;
	}

	/**
	 * <b>保存订单</b>
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	public boolean saveOrder(HotelOrder hotelOrder) throws Exception {
		int count = hotelOrderDao.saveOrder(hotelOrder);
		if (count > 0) {
			return true;
		}
		return false;
	}

	/**
	 * <b>修改订单</b>
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	public boolean updateOrder(HotelOrder hotelOrder) throws Exception {
			int count = hotelOrderDao.updateOrder(hotelOrder);
			if (count > 0) {
				return true;
			}
			return false;
	}
}
