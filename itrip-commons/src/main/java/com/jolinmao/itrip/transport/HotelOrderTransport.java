package com.jolinmao.itrip.transport;

import com.jolinmao.itrip.pojo.entity.HotelOrder;
import com.jolinmao.itrip.pojo.vo.Page;
import com.jolinmao.itrip.pojo.vo.SearchOrderVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @auth jolinmao
 * @date 2022 07 02
 */
@FeignClient(name = "itrip-biz-provider")
@RequestMapping("/hotelorder/trans")
public interface HotelOrderTransport {

	/**
	 * <b>查询订单信息</b>
	 * @param queryOrder
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/getHotelOrderByQuery")
	List<HotelOrder> getHotelOrderByQuery(@RequestBody HotelOrder queryOrder) throws Exception;

	/**
	 * <b>根据订单编号查询订单信息</b>
	 * @param queryOrder
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/getHotelOrderByOrderNo")
	HotelOrder getHotelOrderByOrderNo(@RequestBody HotelOrder queryOrder) throws Exception;

	/**
	 * <b>根据订单id查询订单信息</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/getHotelOrderByOrderId")
	HotelOrder getHotelOrderByOrderId(@RequestParam String orderId) throws Exception;

	/**
	 * <b>保存订单</b>
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/saveOrder")
	boolean saveOrder(@RequestBody HotelOrder hotelOrder) throws Exception;

	/**
	 * <b>修改订单</b>
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "update")
	boolean updateOrder(@RequestBody HotelOrder hotelOrder) throws Exception;

	/**
	 * <b>查询个人订单列表，并分页显示</b>
	 * @param searchOrderVO
	 * @return
	 */
	@PostMapping(value = "/getpersonalorderlist")
	Page<HotelOrder> getPersonalOrderList(@RequestBody SearchOrderVO searchOrderVO);
}
