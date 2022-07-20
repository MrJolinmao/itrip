package com.jolinmao.itrip.controller;

import com.jolinmao.itrip.base.controller.BaseController;
import com.jolinmao.itrip.base.enums.OrderStatusEnum;
import com.jolinmao.itrip.base.pojo.vo.ResponseDto;
import com.jolinmao.itrip.pojo.entity.*;
import com.jolinmao.itrip.pojo.vo.*;
import com.jolinmao.itrip.transport.HotelOrderTransport;
import com.jolinmao.itrip.transport.HotelRoomTransport;
import com.jolinmao.itrip.transport.HotelTransport;
import com.jolinmao.itrip.transport.UserTransport;
import com.jolinmao.itrip.util.MD5Util;
import com.jolinmao.itrip.util.OrderNoUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>酒店订单模块</b>
 * @auth jolinmao
 * @date 2022 07 02
 */
@Api(tags = "酒店订单模块")
@RestController
@RequestMapping("biz/api/hotelorder")
public class HotelOrderController extends BaseController {
	@Autowired
	private HotelTransport hotelTransport;
	@Autowired
	private HotelRoomTransport hotelRoomTransport;
	@Autowired
	private UserTransport userTransport;
	@Autowired
	private HotelOrderTransport hotelOrderTransport;


	/**
	 * <b>生成订单前,获取预订信息</b>
	 *
	 * @param validateRoomStoreVO
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "生成订单前,获取预订信息")
	@ApiResponses({
			@ApiResponse(code = 200, message = "响应成功", response = RoomStoreVO.class, reference = "true")
	})
	@PostMapping(value = "/getpreorderinfo")
	public ResponseDto<Object> getPreorderInfo(@RequestBody ValidateRoomStoreVO validateRoomStoreVO) throws Exception {
		// 根据 hotelId 查询对应的 Hotel 对象
		RoomStoreVO roomStoreVO = new RoomStoreVO();
		Hotel hotel = hotelTransport.getHotelById(validateRoomStoreVO.getHotelId());
		roomStoreVO.setHotelId(hotel.getId());
		roomStoreVO.setHotelName(hotel.getHotelName());

		// 根据 roomId 查询对应的 HotelRoom 对象
		HotelRoom hotelRoom = hotelRoomTransport.getHotelRoomById(validateRoomStoreVO.getRoomId());
		roomStoreVO.setRoomId(hotelRoom.getId());
		roomStoreVO.setPrice(hotelRoom.getRoomPrice());

		// 根据入住时间和退房时间，查询该房间所剩数量
		int store = hotelRoomTransport.queryHotelRoomStoreByDate(validateRoomStoreVO);
		roomStoreVO.setStore(store);
		roomStoreVO.setCheckInDate(validateRoomStoreVO.getCheckInDate());
		roomStoreVO.setCheckOutDate(validateRoomStoreVO.getCheckOutDate());
		roomStoreVO.setCount(validateRoomStoreVO.getCount());

		return ResponseDto.success(roomStoreVO);
	}

	/**
	 * <b>生成订单</b>
	 *
	 * @param addHotelOrderVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/addhotelorder")
	public ResponseDto<Object> addHotelOrder(@RequestBody AddHotelOrderVO addHotelOrderVO) throws Exception {
		// 查询此时是否有房
		ValidateRoomStoreVO validateRoomStoreVO = new ValidateRoomStoreVO();
		BeanUtils.copyProperties(addHotelOrderVO, validateRoomStoreVO);
		int store = hotelRoomTransport.queryHotelRoomStoreByDate(validateRoomStoreVO);
		if (store > addHotelOrderVO.getCount()) {
			// 有房的情况下，保存订单数据表
			// 创建HotelOrder对象
			Hotel hotel = new Hotel();
			String userCode = "";
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				if ("user".equals(cookie.getName())) {
					userCode = cookie.getValue();
				}
			}
			User query = new User();
			query.setUserCode(userCode);
			HotelOrder hotelOrder = new HotelOrder();
			hotelOrder.setUserId(userTransport.getUserListByQuery(query).get(0).getId());
			Integer bookingDays = (int) ((addHotelOrderVO.getCheckOutDate().getTime() - addHotelOrderVO.getCheckInDate().getTime()) / (3600 * 24 * 1000));
			hotelOrder.setBookingDays(bookingDays);

			BeanUtils.copyProperties(addHotelOrderVO, hotelOrder);
			// 订单编号
			String orderNo = OrderNoUtil.createOrderNo(addHotelOrderVO.getHotelId(), addHotelOrderVO.getRoomId());
			hotelOrder.setOrderNo(orderNo);
			// 交易标号
			hotelOrder.setTradeNo(MD5Util.encrypt(String.valueOf(System.currentTimeMillis())));
			// 订单状态
			hotelOrder.setOrderStatus(OrderStatusEnum.ORDER_STATUS_PREPAY.getCode());
			// 订单价格
			hotelOrder.setPayAmount(hotelRoomTransport.getHotelRoomById(addHotelOrderVO.getRoomId()).getRoomPrice() * addHotelOrderVO.getCount());
			// 创建订单时间
			hotelOrder.setCreationDate(new Date());
			System.out.println(hotelOrder.getCreationDate());
			// 添加联系人信息
			StringBuffer sb = new StringBuffer();
			List<UserLinkUser> userLinkUserList = addHotelOrderVO.getLinkUser();
			for (UserLinkUser userLinkUser : userLinkUserList) {
				sb.append(userLinkUser.getLinkUserName() + ",");
			}
			hotelOrder.setLinkUserName(sb.toString().substring(0, sb.toString().length() - 1));

			boolean flag = hotelOrderTransport.saveOrder(hotelOrder);
			if (flag) {
				HotelOrder queryOrder = new HotelOrder();
				queryOrder.setOrderNo(orderNo);
				HotelOrder order = hotelOrderTransport.getHotelOrderByOrderNo(queryOrder);
				// 返回结果
				Map<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("id", order.getId());
				resultMap.put("orderNo", order.getOrderNo());
				return ResponseDto.success(resultMap);
			}
			return ResponseDto.failure("下单失败");
		} else {
			return ResponseDto.failure("房间不足");
		}
	}

	/**
	 * <b>根据订单ID查看个人订单详情</b>
	 *
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/getpersonalorderinfo/{orderId}")
	public ResponseDto<Object> getPersonalOrderInfo(@PathVariable("orderId") String orderId) throws Exception {
		HotelOrder hotelOrder = hotelOrderTransport.getHotelOrderByOrderId(orderId);
		PersonalHotelOrderVO personalHotelOrderVO = new PersonalHotelOrderVO();
		BeanUtils.copyProperties(hotelOrder, personalHotelOrderVO);
		return ResponseDto.success(personalHotelOrderVO);
	}

	/**
	 * <b>根据订单ID查看个人订单详情-房型相关信息</b>
	 *
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/getpersonalorderroominfo/{orderId}")
	public ResponseDto<Object> getPersonalOrderRoomInfo(@PathVariable("orderId") String orderId) throws Exception {
		HotelOrder hotelOrder = hotelOrderTransport.getHotelOrderByOrderId(orderId);
		HotelRoom hotelRoom = hotelRoomTransport.getHotelRoomById(hotelOrder.getRoomId());
		Hotel hotel = hotelTransport.getHotelById(hotelRoom.getHotelId());
		PersonalOrderRoomVO personalOrderRoomVO = new PersonalOrderRoomVO();
		BeanUtils.copyProperties(hotelRoom, personalOrderRoomVO);
		BeanUtils.copyProperties(hotelOrder, personalOrderRoomVO);
		personalOrderRoomVO.setHotelName(hotel.getHotelName());
		personalOrderRoomVO.setHotelLevel(hotel.getHotelLevel());
		return ResponseDto.success(personalOrderRoomVO);
	}

	/**
	 * <b>根据订单ID获取订单信息</b>
	 *
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/queryOrderById/{orderId}")
	public ResponseDto<Object> queryOrderById(@PathVariable("orderId") String orderId) throws Exception {
		HotelOrder hotelOrder = hotelOrderTransport.getHotelOrderByOrderId(orderId);
		return ResponseDto.success(hotelOrder);
	}

	/**
	 * <b>查询个人订单列表，并分页显示</b>
	 *
	 * @param searchOrderVO
	 * @return
	 */
	@ApiOperation(value = "查询个人订单列表，并分页显示")
	@ApiResponses({
			@ApiResponse(code = 200, message = "相映成功", response = Page.class, reference = "true")
	})
	@PostMapping(value = "/getpersonalorderlist")
	public ResponseDto<Object> getPersonalOrderList(@RequestBody SearchOrderVO searchOrderVO) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie: cookies) {
				if ("user".equals(cookie.getName())) {
					String value = cookie.getValue();
					System.out.println(value);
					User queryUser = new User();
					queryUser.setUserCode(value);
					try {
						User user = userTransport.getUserListByQuery(queryUser).get(0);
						searchOrderVO.setLinkUserName(user.getUserName());
						Page<HotelOrder> page = hotelOrderTransport.getPersonalOrderList(searchOrderVO);
						return ResponseDto.success(page);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
		}
		return ResponseDto.failure("请登录");
	}
}
