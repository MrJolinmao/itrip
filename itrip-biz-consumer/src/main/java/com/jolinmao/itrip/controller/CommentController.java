package com.jolinmao.itrip.controller;

import com.jolinmao.itrip.base.controller.BaseController;
import com.jolinmao.itrip.base.pojo.vo.ResponseDto;
import com.jolinmao.itrip.pojo.entity.Comment;
import com.jolinmao.itrip.pojo.entity.HotelImage;
import com.jolinmao.itrip.pojo.entity.ScoreComment;
import com.jolinmao.itrip.pojo.vo.CommentCountVO;
import com.jolinmao.itrip.pojo.vo.Page;
import com.jolinmao.itrip.pojo.vo.SearchCommentVO;
import com.jolinmao.itrip.transport.CommentTransport;
import com.jolinmao.itrip.transport.HotelRoomTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auth jolinmao
 * @date 2022 06 30
 */
@RestController("commentController")
@RequestMapping("biz/api/comment")
public class CommentController extends BaseController {
	@Autowired
	private CommentTransport commentTransport;
	@Autowired
	private HotelRoomTransport hotelRoomTransport;

	/**
	 * <b>据酒店id查询酒店平均分</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/gethotelscore/{hotelId}")
	public ResponseDto<Object> getHotelScore(@PathVariable("hotelId") Long hotelId) throws Exception {
		ScoreComment scoreComment =commentTransport.getHotelScore(hotelId);
		return ResponseDto.success(scoreComment);
	}

	/**
	 * <b>根据酒店id查询各类评论数量</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/getcount/{hotelId}")
	public ResponseDto<Object> getCount(@PathVariable("hotelId") Long hotelId) throws Exception {
		CommentCountVO commentCountVO = commentTransport.getCommentCountByHotelId(hotelId);
		return ResponseDto.success(commentCountVO);
	}

	/**
	 * <b>根据评论类型查询评论列表，并分页显示</b>
	 * @param queryVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/getcommentlist")
	public ResponseDto<Object> getCommentListByPage(@RequestBody SearchCommentVO queryVO) throws Exception {
		// 使用数据持久层查询分页信息
		Page<Comment> page = commentTransport.getCommentListByPage(queryVO);
		return ResponseDto.success(page);
	}

	/**
	 * <b>根据targetId查询酒店房型图片(type=2)</b>
	 * @param targetId
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/getimg/{targetId}")
	public ResponseDto<Object> getImgByTargetId(@PathVariable("targetId") Long targetId) throws Exception {
		List<HotelImage> imageList = hotelRoomTransport.getImgByTargetIdAndTypeId(targetId, "2");
		return ResponseDto.success(imageList);
	}
}
