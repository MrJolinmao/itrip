package com.jolinmao.itrip.dao;

import com.jolinmao.itrip.pojo.entity.HotelImage;
import com.jolinmao.itrip.pojo.entity.HotelRoom;
import com.jolinmao.itrip.pojo.vo.HotelRoomVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @auth jolinmao
 * @date 2022 06 29
 */
@Repository
public interface HotelRoomDao {

	/**
	 * <b>根据酒店id查询该酒店所有房间信息列表</b>
	 * @param queryMap
	 * @return
	 * @throws Exception
	 */
	List<HotelRoom> findHotelRoomListByQuery(Map<String, Object> queryMap) throws Exception;

	/**
	 * <b>查询临时库存</b>
	 * @param queryMap
	 * @return
	 * @throws Exception
	 */
	Integer queryTempStore(Map<String, Object> queryMap) throws Exception;

	/**
	 * <b>查询总库存</b>
	 * @param queryMap
	 * @return
	 * @throws Exception
	 */
	Integer queryTotalStore(Map<String, Object> queryMap) throws Exception;
}
