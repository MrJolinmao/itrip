package com.jolinmao.itrip.dao;

import com.jolinmao.itrip.pojo.entity.HotelImage;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @auth jolinmao
 * @date 2022 07 02
 */
@Repository
public interface HotelImageDao {

	/**
	 * <b>根据targetId查询评论照片(type=2)</b>
	 * @param queryMap
	 * @return
	 * @throws Exception
	 */
	List<HotelImage> getImgByTargetIdAndTypeId(Map<String, Object> queryMap) throws Exception;
}
