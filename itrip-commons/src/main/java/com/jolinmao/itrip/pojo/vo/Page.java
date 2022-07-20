package com.jolinmao.itrip.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @auth jolinmao
 * @date 2022 07 01
 */
@ApiModel(value = "Page<T>", description = "分页对象")
public class Page<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "当前页数")
	private Integer curPage;        // 当前页数
	@ApiModelProperty(value = "总条数")
	private Integer total;          // 总条数
	@ApiModelProperty(value = "页面大小")
	private Integer pageSize;       // 页面大小
	@ApiModelProperty(value = "总页数")
	private Integer pageCount;      // 总页数
	@ApiModelProperty(value = "起始位置")
	private Integer beginPos;       // 起始位置
	@ApiModelProperty(value = "分页内容")
	private List<T> rows;           // 分页内容

	public Page() {}

	public Page(Integer curPage, Integer total, Integer pageSize) {
		this.curPage = curPage;
		this.total = total;
		this.pageSize = pageSize;
	}

	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getBeginPos() {
		return beginPos;
	}

	public void setBeginPos(Integer beginPos) {
		this.beginPos = beginPos;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
