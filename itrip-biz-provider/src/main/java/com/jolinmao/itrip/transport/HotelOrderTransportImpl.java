package com.jolinmao.itrip.transport;

import com.jolinmao.itrip.pojo.entity.HotelOrder;
import com.jolinmao.itrip.pojo.vo.Page;
import com.jolinmao.itrip.pojo.vo.SearchOrderVO;
import com.jolinmao.itrip.service.HotelOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auth jolinmao
 * @date 2022 07 02
 */
@RestController
@RequestMapping("/hotelorder/trans")
public class HotelOrderTransportImpl implements HotelOrderTransport {
	@Autowired
	private HotelOrderService hotelOrderService;

	/**
	 * <b>查询订单信息</b>
	 * @param queryOrder
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/getHotelOrderByQuery")
	public List<HotelOrder> getHotelOrderByQuery(@RequestBody HotelOrder queryOrder) throws Exception {
		return hotelOrderService.getHotelOrderByQuery(queryOrder);
	}

	/**
	 * <b>根据订单编号查询订单信息</b>
	 * @param queryOrder
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/getHotelOrderByOrderNo")
	public HotelOrder getHotelOrderByOrderNo(@RequestBody HotelOrder queryOrder) throws Exception {
		return hotelOrderService.getHotelOrderByOrderNo(queryOrder);
	}

	/**
	 * <b>根据订单id查询订单信息</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/getHotelOrderByOrderId")
	public HotelOrder getHotelOrderByOrderId(@RequestParam String orderId) throws Exception {
		return hotelOrderService.getHotelOrderByOrderId(orderId);
	}

	/**
	 * <b>保存订单</b>
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/saveOrder")
	public boolean saveOrder(@RequestBody HotelOrder hotelOrder) throws Exception {
		return hotelOrderService.saveOrder(hotelOrder);
	}

	/**
	 * <b>修改订单</b>
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "update")
	public boolean updateOrder(@RequestBody HotelOrder hotelOrder) throws Exception {
		return hotelOrderService.updateOrder(hotelOrder);
	}

	/**
	 * <b>查询个人订单列表，并分页显示</b>
	 * @param searchOrderVO
	 * @return
	 */
	@PostMapping(value = "/getpersonalorderlist")
	public Page<HotelOrder> getPersonalOrderList(@RequestBody SearchOrderVO searchOrderVO) {
		return hotelOrderService.getPersonalOrderList(searchOrderVO);
	}
}
