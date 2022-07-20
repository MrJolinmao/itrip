package com.jolinmao.itrip.transport;

import com.jolinmao.itrip.pojo.entity.HotelImage;
import com.jolinmao.itrip.pojo.entity.HotelRoom;
import com.jolinmao.itrip.pojo.vo.HotelRoomVO;
import com.jolinmao.itrip.pojo.vo.SearchHotelRoomVO;
import com.jolinmao.itrip.pojo.vo.ValidateRoomStoreVO;
import com.jolinmao.itrip.service.HotelRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <B>酒店房间模块传输层</B>
 * @auth jolinmao
 * @date 2022 06 29
 */
@RestController("hotelRoomTransport")
@RequestMapping("/hootroom/trans")
public class HotelRoomTransportImpl implements HotelRoomTransport {
	@Autowired
	private HotelRoomService hotelRoomService;


	/**
	 * <b>查询酒店房间列表-此刻可以预定的房间列表</b>
	 * @param searchHotelRoomVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/queryHotelRoomByHotel")
	public List<HotelRoomVO> queryHotelRoomByHotel(@RequestBody SearchHotelRoomVO searchHotelRoomVO) throws Exception {
		return hotelRoomService.queryHotelRoomByHotel(searchHotelRoomVO);
	}

	/**
	 * <b>根据targetId查询评论照片(type=2)</b>
	 * @param targetId
	 * @param type
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/getImgByTargetIdAndTypeId")
	public List<HotelImage> getImgByTargetIdAndTypeId(@RequestParam Long targetId, String type) throws Exception {
		return hotelRoomService.getImgByTargetIdAndTypeId(targetId, type);
	}

	/**
	 * <b>根据 roomId 查询对应的 HotelRoom 对象</b>
	 * @param roomId
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/getHotelRoomById")
	public HotelRoom getHotelRoomById(@RequestParam Long roomId) throws Exception {
		return hotelRoomService.getHotelRoomById(roomId);
	}

	/**
	 * <b>根据入住时间和退房时间，查询该房间所剩数量</b>
	 * @param validateRoomStoreVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/queryHotelRoomStoreByDate")
	public int queryHotelRoomStoreByDate(@RequestBody ValidateRoomStoreVO validateRoomStoreVO) throws Exception {
		return hotelRoomService.queryHotelRoomStoreByDate(validateRoomStoreVO);
	}
}
