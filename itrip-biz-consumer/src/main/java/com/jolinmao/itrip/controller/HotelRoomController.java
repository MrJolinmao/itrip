package com.jolinmao.itrip.controller;

import com.jolinmao.itrip.base.pojo.vo.ResponseDto;
import com.jolinmao.itrip.pojo.entity.HotelImage;
import com.jolinmao.itrip.pojo.entity.LabelDic;
import com.jolinmao.itrip.pojo.vo.HotelRoomVO;
import com.jolinmao.itrip.pojo.vo.SearchHotelRoomVO;
import com.jolinmao.itrip.transport.HotelRoomTransport;
import com.jolinmao.itrip.transport.LabelDicTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>酒店房间控制模块</b>
 * @auth jolinmao
 * @date 2022 06 29
 */
@RestController("hotelRoomController")
@RequestMapping("biz/api/hotelroom")
public class HotelRoomController {
	@Autowired
	private HotelRoomTransport hotelRoomTransport;
	@Autowired
	private LabelDicTransport labelDicTransport;

	/**
	 * <B>查询酒店房间床型列表</B>
	 * @return
	 * @throws ExceptionInInitializerError
	 */
	@GetMapping(value = "/queryhotelroombed")
	public ResponseDto<Object> queryHotelRoomBed() throws Exception {
		LabelDic query = new LabelDic();
		query.setParentId(1L);
		List<LabelDic> labelDicList = labelDicTransport.queryHotelRoomBed(query);
		return ResponseDto.success(labelDicList);
	}

	/**
	 * <B>查询酒店房间列表-此刻可以预定的房间列表</B>
	 * @param searchHotelRoomVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/queryhotelroombyhotel")
	public ResponseDto<Object> queryHotelRoomByHotel(@RequestBody SearchHotelRoomVO searchHotelRoomVO) throws Exception {
		List<List<HotelRoomVO>> resultList = new ArrayList<List<HotelRoomVO>>();
		// 查询可用酒店房间列表
		List<HotelRoomVO> hotelRoomList = hotelRoomTransport.queryHotelRoomByHotel(searchHotelRoomVO);
		// 循环遍历集合
		if (hotelRoomList != null && hotelRoomList.size() > 0) {
			for (HotelRoomVO hotelRoomVO: hotelRoomList ) {
				List<HotelRoomVO> hotelRoomVOList = new ArrayList<HotelRoomVO>();
				hotelRoomVOList.add(hotelRoomVO);
				resultList.add(hotelRoomVOList);
			}
		}
		return ResponseDto.success(resultList);
	}

	/**
	 * <b>根据targetId查询酒店房型图片(type=1)</b>
	 * @param targetId
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/getimg/{targetId}")
	public ResponseDto<Object> getImgByTargetId(@PathVariable("targetId") Long targetId) throws Exception {
		List<HotelImage> imageList = hotelRoomTransport.getImgByTargetIdAndTypeId(targetId, "1");
		return ResponseDto.success(imageList);
	}
}
