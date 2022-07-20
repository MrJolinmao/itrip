package com.jolinmao.itrip.transport;

import com.jolinmao.itrip.pojo.entity.AreaDic;
import com.jolinmao.itrip.service.AreaDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <b>爱旅行-区域字典信息传输层接口</b>
 * @author Jolinmao
 * @version 1.0.0
 * @since 2020 03 01
 */
@RestController("areaDicTransport")
@RequestMapping("/areadic/trans")
public class AreaDicTransportImpl implements AreaDicTransport {
	@Autowired
	private AreaDicService areaDicService;

	/**
	 * <b>查询热门城市</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/queryHotCityByQuery")
	public List<AreaDic> queryHotCityByQuery(@RequestBody AreaDic query) throws Exception {
		return areaDicService.queryHotCityByQuery(query);
	}
}
