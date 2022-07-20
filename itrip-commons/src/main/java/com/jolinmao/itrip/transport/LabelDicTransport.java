package com.jolinmao.itrip.transport;

import com.jolinmao.itrip.pojo.entity.LabelDic;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <b>爱旅行-酒店字典信息传输层接口</b>
 * @author Jolinmao
 * @version 1.0.0
 * @since 2020 03 01
 */
@FeignClient(name = "itrip-biz-provider")
@RequestMapping("/labeldic/trans")
public interface LabelDicTransport {

	/**
	 * <b>查询酒店特色列表</b>
	 * @param labelDic
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/queryhotelfeature")
	List<LabelDic> queryhotelfeature(@RequestBody LabelDic labelDic) throws Exception;

	/**
	 * <b>查询酒店房间床型列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/queryHotelRoomBed")
	List<LabelDic> queryHotelRoomBed(@RequestBody LabelDic query) throws Exception;
}
