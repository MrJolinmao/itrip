package com.jolinmao.itrip.service;

import com.jolinmao.itrip.pojo.entity.HotelOrder;
import com.jolinmao.itrip.pojo.vo.Page;
import com.jolinmao.itrip.pojo.vo.SearchOrderVO;

import java.util.List;

/**
 * @auth jolinmao
 * @date 2022 07 02
 */
public interface HotelOrderService {

	/**
	 * <b>查询订单信息</b>
	 * @param queryOrder
	 * @return
	 * @throws Exception
	 */
	List<HotelOrder> getHotelOrderByQuery(HotelOrder queryOrder) throws Exception;

	/**
	 * <b>根据订单编号查询订单信息</b>
	 * @param queryOrder
	 * @return
	 * @throws Exception
	 */
	HotelOrder getHotelOrderByOrderNo(HotelOrder queryOrder) throws Exception;

	/**
	 * <b>保存订单</b>
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	boolean saveOrder(HotelOrder hotelOrder) throws Exception;

	/**
	 * <b>修改订单</b>
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	boolean updateOrder(HotelOrder hotelOrder) throws Exception;

	/**
	 * <b>根据订单id查询订单信息</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	HotelOrder getHotelOrderByOrderId(String orderId)throws Exception;

	/**
	 * <b>查询个人订单列表，并分页显示</b>
	 * @param searchOrderVO
	 * @return
	 */
    Page<HotelOrder> getPersonalOrderList(SearchOrderVO searchOrderVO);
}
