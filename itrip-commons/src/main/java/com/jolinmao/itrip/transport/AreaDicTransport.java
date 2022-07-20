package com.jolinmao.itrip.transport;

import com.jolinmao.itrip.pojo.entity.AreaDic;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <b>爱旅行-区域字典信息传输层接口</b>
 * @author Jolinmao
 * @version 1.0.0
 * @since 2020 03 01
 */

@FeignClient(name = "itrip-biz-provider")
@RequestMapping("/areadic/trans")
public interface AreaDicTransport {

	/**
	 * <b>查询热门城市</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/queryHotCityByQuery")
	List<AreaDic> queryHotCityByQuery(@RequestBody AreaDic query) throws Exception;
}
