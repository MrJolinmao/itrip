package com.jolinmao.itrip.transport;

import com.jolinmao.itrip.pojo.entity.Comment;
import com.jolinmao.itrip.pojo.entity.ScoreComment;
import com.jolinmao.itrip.pojo.vo.CommentCountVO;
import com.jolinmao.itrip.pojo.vo.Page;
import com.jolinmao.itrip.pojo.vo.SearchCommentVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <b>评论模块</b>
 * @auth jolinmao
 * @date 2022 06 30
 */
@FeignClient(name = "itrip-biz-provider")
@RequestMapping("/comment/trans")
public interface CommentTransport {

	/**
	 * <b>根据酒店id查询各类评论数量</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/gethotelscore")
	ScoreComment getHotelScore(@RequestParam Long hotelId) throws Exception;


	/**
	 * <b>根据酒店id查询各类评论数量</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/getCommentCountByHotelId")
	CommentCountVO getCommentCountByHotelId(@RequestParam Long hotelId) throws Exception;

	/**
	 * <b>根据评论类型查询评论列表，并分页显示</b>
	 * @param queryVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/getCommentListByPage")
	Page<Comment> getCommentListByPage(@RequestBody SearchCommentVO queryVO) throws Exception;
}
