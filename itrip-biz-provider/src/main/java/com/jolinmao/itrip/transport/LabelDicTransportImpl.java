package com.jolinmao.itrip.transport;

import com.jolinmao.itrip.pojo.entity.LabelDic;
import com.jolinmao.itrip.service.LabelDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <b>爱旅行-酒店字典信息传输层接口</b>
 * @author Jolinmao
 * @version 1.0.0
 * @since 2020 03 01
 */
@RestController("labelDicTransport")
@RequestMapping("/labeldic/trans")
public class LabelDicTransportImpl implements LabelDicTransport {
	@Autowired
	private LabelDicService labelDicService;

	/**
	 * <b>查询酒店特色列表</b>
	 * @param labelDic
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/queryhotelfeature")
	public List<LabelDic> queryhotelfeature(@RequestBody LabelDic labelDic) throws Exception {
		return labelDicService.queryhotelfeature(labelDic);
	}

	/**
	 * <b>查询酒店房间床型列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/queryHotelRoomBed")
	public List<LabelDic> queryHotelRoomBed(@RequestBody LabelDic query) throws Exception {
		return labelDicService.queryHotelRoomBed(query);
	}
}
