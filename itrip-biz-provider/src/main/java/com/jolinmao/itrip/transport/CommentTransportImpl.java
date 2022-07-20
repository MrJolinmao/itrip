package com.jolinmao.itrip.transport;

import com.jolinmao.itrip.pojo.entity.Comment;
import com.jolinmao.itrip.pojo.entity.ScoreComment;
import com.jolinmao.itrip.pojo.vo.CommentCountVO;
import com.jolinmao.itrip.pojo.vo.Page;
import com.jolinmao.itrip.pojo.vo.SearchCommentVO;
import com.jolinmao.itrip.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @auth jolinmao
 * @date 2022 06 30
 */
@RestController("commentTransport")
@RequestMapping("/comment/trans")
public class CommentTransportImpl implements CommentTransport {
	@Autowired
	private CommentService commentService;

	/**
	 * <b>根据酒店id查询各类评论数量</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/gethotelscore")
	public ScoreComment getHotelScore(@RequestParam Long hotelId) throws Exception {
		return commentService.getHotelScore(hotelId);
	}


	/**
	 * <b>根据酒店id查询各类评论数量</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/getCommentCountByHotelId")
	public CommentCountVO getCommentCountByHotelId(@RequestParam Long hotelId) throws Exception {
		return commentService.getCommentCountByHotelId(hotelId);
	}

	/**
	 * <b>根据评论类型查询评论列表，并分页显示</b>
	 * @param queryVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/getCommentListByPage")
	public Page<Comment> getCommentListByPage(@RequestBody SearchCommentVO queryVO) throws Exception {
		return commentService.getCommentListByPage(queryVO);
	}
}
