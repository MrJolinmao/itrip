package com.jolinmao.itrip.dao;

import com.jolinmao.itrip.pojo.entity.Comment;
import com.jolinmao.itrip.pojo.entity.ScoreComment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @auth jolinmao
 * @date 2022 06 30
 */
@Repository
public interface CommentDao {

	/**
	 * <b>根据酒店id查询各类评论数量</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	ScoreComment getHotelScore(Long hotelId) throws Exception;

	/**
	 * <b>根据条件查询评论列表</b>
	 * @param queryMap
	 * @return
	 */
	List<Comment> findCommentListByQuery(Map<String, Object> queryMap) throws Exception;
}
