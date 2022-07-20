package com.jolinmao.itrip.dao;

import com.jolinmao.itrip.pojo.entity.LabelDic;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <b>爱旅行-酒店字典信息传输层接口</b>
 * @author Jolinmao
 * @version 1.0.0
 * @since 2020 03 01
 */
@Repository
public interface LabelDicDao {

	/**
	 * <b>查询酒店特色列表</b>
	 * @param labelDic
	 * @return
	 * @throws Exception
	 */
	List<LabelDic> queryhotelfeature(LabelDic labelDic) throws Exception;

	/**
	 * <b>查询酒店房间床型列表</b>
	 * @param labelDic
	 * @return
	 * @throws Exception
	 */
	List<LabelDic> queryHotelRoomBed(LabelDic labelDic) throws Exception;
}
