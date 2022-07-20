package com.jolinmao.itrip.service.impl;

import com.jolinmao.itrip.dao.LabelDicDao;
import com.jolinmao.itrip.pojo.entity.LabelDic;
import com.jolinmao.itrip.service.LabelDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>爱旅行-酒店字典信息传输层接口</b>
 * @author Jolinmao
 * @version 1.0.0
 * @since 2020 03 01
 */
@Service("labelDicService")
@Transactional
public class LabelDicServiceImpl implements LabelDicService {
	@Autowired
	private LabelDicDao labelDicDao;

	/**
	 * <b>查询酒店特色列表</b>
	 * @param labelDic
	 * @return
	 * @throws Exception
	 */
	public List<LabelDic> queryhotelfeature(LabelDic labelDic) throws Exception {
		List<LabelDic> labelDicList = labelDicDao.queryhotelfeature(labelDic);
		if (labelDicList != null) {
			return labelDicList;
		}
		return new ArrayList<LabelDic>();
	}

	/**
	 * <b>查询酒店房间床型列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public List<LabelDic> queryHotelRoomBed(LabelDic query) throws Exception {
		List<LabelDic> labelDicList = labelDicDao.queryHotelRoomBed(query);
		if (labelDicList != null) {
			return labelDicList;
		}
		return new ArrayList<LabelDic>();
	}
}
