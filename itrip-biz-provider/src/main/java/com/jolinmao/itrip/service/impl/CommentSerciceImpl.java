package com.jolinmao.itrip.service.impl;

import com.jolinmao.itrip.dao.CommentDao;
import com.jolinmao.itrip.pojo.entity.Comment;
import com.jolinmao.itrip.pojo.entity.ScoreComment;
import com.jolinmao.itrip.pojo.vo.CommentCountVO;
import com.jolinmao.itrip.pojo.vo.Page;
import com.jolinmao.itrip.pojo.vo.SearchCommentVO;
import com.jolinmao.itrip.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auth jolinmao
 * @date 2022 06 30
 */
@Service("commentService")
@Transactional
public class CommentSerciceImpl implements CommentService {
	@Autowired
	private CommentDao commentDao;

	/**
	 * <b>根据酒店id查询各类评论数量</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	public ScoreComment getHotelScore(Long hotelId) throws Exception {
		return commentDao.getHotelScore(hotelId);
	}

	/**
	 * <b>根据酒店id查询各类评论数量</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	public CommentCountVO getCommentCountByHotelId(Long hotelId) throws Exception {
		CommentCountVO commentCountVO = new CommentCountVO();
		// 设定查询参数
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("hotelId", hotelId);
		// 查询总评论数
		commentCountVO.setAllcomment(commentDao.findCommentListByQuery(queryMap).size());
		// 查询值得推荐
		queryMap.put("isOk", 1);
		commentCountVO.setIsok(commentDao.findCommentListByQuery(queryMap).size());
		// 值得改善
		queryMap.put("isOk", 0);
		commentCountVO.setImprove(commentDao.findCommentListByQuery(queryMap).size());
		// 有图片
		queryMap.remove("isOk");
		queryMap.put("isHavingImg", 1);
		commentCountVO.setHavingimg(commentDao.findCommentListByQuery(queryMap).size());

		return commentCountVO;
	}

	/**
	 * <b>根据评论类型查询评论列表，并分页显示</b>
	 * @param queryVO
	 * @return
	 * @throws Exception
	 */
	public Page<Comment> getCommentListByPage(SearchCommentVO queryVO) throws Exception {
		// 根据查询视图进行封装查询Map集合
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("hotelId", queryVO.getHotelId());
		if (queryVO.getIsOk() != -1) {
			queryMap.put("isOk", queryVO.getIsOk());
		}
		if (queryVO.getIsHavingImg() != -1) {
			queryMap.put("isHavingImg", queryVO.getIsHavingImg());
		}
		queryMap.put("star", (queryVO.getPageNo() - 1) * queryVO.getPageSize());
		queryMap.put("size", queryVO.getPageSize());
		// 获取分页列表
		List<Comment> commentList = commentDao.findCommentListByQuery(queryMap);
		// 获得总条数
		queryMap.remove("star");
		queryMap.remove("size");
		Integer total = commentDao.findCommentListByQuery(queryMap).size();
		// 封装分页对象
		Page<Comment> page = new Page<Comment>(queryVO.getPageNo(), total, queryVO.getPageSize());
		page.setPageCount(total % queryVO.getPageSize() == 0 ? total % queryVO.getPageSize() : total % queryVO.getPageSize() + 1);
		page.setBeginPos((queryVO.getPageNo() - 1) * queryVO.getPageSize());
		page.setRows(commentList);
		return page;
	}
}
