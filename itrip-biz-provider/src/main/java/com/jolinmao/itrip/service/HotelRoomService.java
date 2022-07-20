package com.jolinmao.itrip.service;

import com.jolinmao.itrip.pojo.entity.HotelImage;
import com.jolinmao.itrip.pojo.entity.HotelRoom;
import com.jolinmao.itrip.pojo.vo.HotelRoomVO;
import com.jolinmao.itrip.pojo.vo.SearchHotelRoomVO;
import com.jolinmao.itrip.pojo.vo.ValidateRoomStoreVO;

import java.util.List;

/**
 * <b></b>
 * @auth jolinmao
 * @date 2022 06 29
 */
public interface HotelRoomService {

	/**
	 * <b>查询酒店房间列表-此刻可以预定的房间列表</b>
	 * @param searchHotelRoomVO
	 * @return
	 * @throws Exception
	 */
	List<HotelRoomVO> queryHotelRoomByHotel(SearchHotelRoomVO searchHotelRoomVO) throws Exception;

	/**
	 * <b>根据targetId查询评论照片(type=2)</b>
	 * @param targetId
	 * @param type
	 * @return
	 * @throws Exception
	 */
	List<HotelImage> getImgByTargetIdAndTypeId(Long targetId, String type) throws Exception;

	/**
	 * <b>根据 roomId 查询对应的 HotelRoom 对象</b>
	 * @param roomId
	 * @return
	 * @throws Exception
	 */
	HotelRoom getHotelRoomById(Long roomId) throws Exception;

	/**
	 * <b>根据入住时间和退房时间，查询该房间所剩数量</b>
	 * @param validateRoomStoreVO
	 * @return
	 * @throws Exception
	 */
	int queryHotelRoomStoreByDate(ValidateRoomStoreVO validateRoomStoreVO) throws Exception;
}
