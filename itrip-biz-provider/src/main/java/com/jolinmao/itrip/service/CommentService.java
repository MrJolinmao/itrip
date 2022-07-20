package com.jolinmao.itrip.service;

import com.jolinmao.itrip.pojo.entity.Comment;
import com.jolinmao.itrip.pojo.entity.ScoreComment;
import com.jolinmao.itrip.pojo.vo.CommentCountVO;
import com.jolinmao.itrip.pojo.vo.Page;
import com.jolinmao.itrip.pojo.vo.SearchCommentVO;

/**
 * @auth jolinmao
 * @date 2022 06 30
 */
public interface CommentService {

	/**
	 * <b>根据酒店id查询各类评论数量</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	ScoreComment getHotelScore(Long hotelId) throws Exception;

	/**
	 * <b>根据酒店id查询各类评论数量</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	CommentCountVO getCommentCountByHotelId(Long hotelId) throws Exception;

	/**
	 * <b>根据评论类型查询评论列表，并分页显示</b>
	 * @param queryVO
	 * @return
	 * @throws Exception
	 */
	Page<Comment> getCommentListByPage(SearchCommentVO queryVO) throws Exception;
}
