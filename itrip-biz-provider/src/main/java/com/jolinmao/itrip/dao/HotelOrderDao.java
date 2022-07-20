package com.jolinmao.itrip.dao;

import com.jolinmao.itrip.pojo.entity.HotelOrder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @auth jolinmao
 * @date 2022 07 02
 */
@Repository
public interface HotelOrderDao {

	/**
	 * <b>查询此时该房间订单表中处于未支付和支付成功的订单数量</b>
	 * @param orderQueryMap
	 * @return
	 * @throws Exception
	 */
	Integer findOrderRoomCountByQuery(Map<String, Object> orderQueryMap) throws Exception;

	/**
	 * <b>查询订单信息</b>
	 * @param queryOrder
	 * @return
	 * @throws Exception
	 */
	List<HotelOrder> getHotelOrderByQuery(HotelOrder queryOrder) throws Exception;

	/**
	 * <b>保存订单</b>
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	int saveOrder(HotelOrder hotelOrder);

	/**
	 * <b>修改订单</b>
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	int updateOrder(HotelOrder hotelOrder);

	/**
	 * <b>查询个人订单列表，并分页显示</b>
	 * @param queryMap
	 * @return
	 */
    List<HotelOrder> getPersonalOrderList(Map<String, Object> queryMap);
}
