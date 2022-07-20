package com.jolinmao.itrip.service.impl;

import com.jolinmao.itrip.dao.HotelImageDao;
import com.jolinmao.itrip.dao.HotelOrderDao;
import com.jolinmao.itrip.dao.HotelRoomDao;
import com.jolinmao.itrip.pojo.entity.HotelImage;
import com.jolinmao.itrip.pojo.entity.HotelRoom;
import com.jolinmao.itrip.pojo.vo.HotelRoomVO;
import com.jolinmao.itrip.pojo.vo.SearchHotelRoomVO;
import com.jolinmao.itrip.pojo.vo.ValidateRoomStoreVO;
import com.jolinmao.itrip.service.HotelRoomService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auth jolinmao
 * @date 2022 06 29
 */
@Service("hotelRoomService")
@Transactional
public class HotelRoomServiceImpl implements HotelRoomService {
	@Autowired
	private HotelRoomDao hotelRoomDao;
	@Autowired
	private HotelImageDao hotelImageDao;
	@Autowired
	private HotelOrderDao hotelOrderDao;

	/**
	 * <b>查询酒店房间列表-此刻可以预定的房间列表</b>
	 * @param searchHotelRoomVO
	 * @return
	 * @throws Exception
	 */
	public List<HotelRoomVO> queryHotelRoomByHotel(SearchHotelRoomVO searchHotelRoomVO) throws Exception {
		// 根据搜索视图，封装查询Map集合
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("hotelId", searchHotelRoomVO.getHotelId());
		queryMap.put("isBook", searchHotelRoomVO.getIsBook());
		queryMap.put("isHavingBreakfast", searchHotelRoomVO.getIsHavingBreakfast());
		queryMap.put("isTimelyResponse", searchHotelRoomVO.getIsTimelyResponse());
		queryMap.put("roomBedTypeId", searchHotelRoomVO.getRoomBedTypeId());
		queryMap.put("startDate", searchHotelRoomVO.getStartDate());
		queryMap.put("endDate", searchHotelRoomVO.getEndDate());
		queryMap.put("isCancel", searchHotelRoomVO.getIsCancel());
		queryMap.put("payType", searchHotelRoomVO.getPayType());

		// 使用数据持久层进行查询

		List<HotelRoom> hotelRoomList = hotelRoomDao.findHotelRoomListByQuery(queryMap);
		// 将实体对象的属性复制到视图对象中
		List<HotelRoomVO> hotelRoomVOList = new ArrayList<HotelRoomVO>();
		if (hotelRoomList != null && hotelRoomList.size() > 0) {
			for (HotelRoom hotelRoom: hotelRoomList) {
				HotelRoomVO hotelRoomVO = new HotelRoomVO();
				BeanUtils.copyProperties(hotelRoom, hotelRoomVO);
				hotelRoomVOList.add(hotelRoomVO);
			}
		}
		return hotelRoomVOList;
	}

	/**
	 * <b>根据targetId查询评论照片(type=2)</b>
	 * @param targetId
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<HotelImage> getImgByTargetIdAndTypeId(Long targetId, String type) throws Exception {
		// 封装查询参数
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("targetId", targetId);
		queryMap.put("type", type);
		List<HotelImage> hotelImageList = hotelImageDao.getImgByTargetIdAndTypeId(queryMap);
		return hotelImageList;
	}

	/**
	 * <b>根据 roomId 查询对应的 HotelRoom 对象</b>
	 * @param roomId
	 * @return
	 * @throws Exception
	 */
	public HotelRoom getHotelRoomById(Long roomId) throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("id", roomId);
		List<HotelRoom> hotelRoomList = hotelRoomDao.findHotelRoomListByQuery(queryMap);
		return hotelRoomList.get(0);
	}

	/**
	 * <b>根据入住时间和退房时间，查询该房间所剩数量</b>
	 * @param validateRoomStoreVO
	 * @return
	 * @throws Exception
	 */
	public int queryHotelRoomStoreByDate(ValidateRoomStoreVO validateRoomStoreVO) throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("roomId", validateRoomStoreVO.getRoomId());
		queryMap.put("beginDate", validateRoomStoreVO.getCheckInDate());
		Integer store = hotelRoomDao.queryTempStore(queryMap);
		if (store == null) {
			// 如果临时库存不存在，查询总库存数量
			queryMap.put("productId", validateRoomStoreVO.getRoomId());
			store = hotelRoomDao.queryTotalStore(queryMap);
		}
		// 计算可用库存，如果库存大于0
		if (store > 0) {
			// 查询此时该房间订单表中处于未支付和支付成功的订单数量
			Map<String, Object> orderQueryMap = new HashMap<String, Object>();
			orderQueryMap.put("roomId", validateRoomStoreVO.getRoomId());
			orderQueryMap.put("startDate", validateRoomStoreVO.getCheckInDate());
			orderQueryMap.put("endDate", validateRoomStoreVO.getCheckOutDate());
			Integer orderRoomCount = hotelOrderDao.findOrderRoomCountByQuery(orderQueryMap);
			// 使用库存-订单输入，如果大于0则说明该房间可用，那么加入最终的结果列表
			if (orderRoomCount == null) {
				return store;
			}
			if ((store - orderRoomCount) > 0) {
				return store - orderRoomCount;
			}
		}
		return 0;
	}
}
