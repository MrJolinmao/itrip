package com.jolinmao.itrip.transport;

import com.jolinmao.itrip.pojo.entity.HotelImage;
import com.jolinmao.itrip.pojo.entity.HotelRoom;
import com.jolinmao.itrip.pojo.vo.HotelRoomVO;
import com.jolinmao.itrip.pojo.vo.SearchHotelRoomVO;
import com.jolinmao.itrip.pojo.vo.ValidateRoomStoreVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @auth jolinmao
 * @date 2022 06 29
 */
@FeignClient(name = "itrip-biz-provider")
@RequestMapping("/hootroom/trans")
public interface HotelRoomTransport {

	/**
	 * <b>查询酒店房间列表-此刻可以预定的房间列表</b>
	 * @param searchHotelRoomVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/queryHotelRoomByHotel")
	List<HotelRoomVO> queryHotelRoomByHotel(@RequestBody SearchHotelRoomVO searchHotelRoomVO) throws Exception;

	/**
	 * <b>根据targetId查询评论照片(type=2)</b>
	 * @param targetId
	 * @param type
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/getImgByTargetIdAndTypeId")
	List<HotelImage> getImgByTargetIdAndTypeId(@RequestParam Long targetId, String type) throws Exception;

	/**
	 * <b>根据 roomId 查询对应的 HotelRoom 对象</b>
	 * @param roomId
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/getHotelRoomById")
	HotelRoom getHotelRoomById(@RequestParam Long roomId) throws Exception;

	/**
	 * <b>根据入住时间和退房时间，查询该房间所剩数量</b>
	 * @param validateRoomStoreVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/queryHotelRoomStoreByDate")
	int queryHotelRoomStoreByDate(@RequestBody ValidateRoomStoreVO validateRoomStoreVO) throws Exception;
}
