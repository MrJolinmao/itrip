package com.jolinmao.itrip.pojo.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * <b>前端输入-查询酒店搜索条件VO</b>
 * @auth jolinmao
 * @date 2022 06 29
 */
public class SearchHotelVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String destination;
	private Integer hotelLevel;
	private String keywords;
	private String tradeAreaIds;
	private Double minPrice;
	private Double maxPrice;
	private String featureIds;
	private String ascSort;
	private String  descSort;
	private Date checkInDate;
	private Date checkOutDate;
	private Integer pageSize;
	private Integer pageNo;

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Integer getHotelLevel() {
		return hotelLevel;
	}

	public void setHotelLevel(Integer hotelLevel) {
		this.hotelLevel = hotelLevel;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getTradeAreaIds() {
		return tradeAreaIds;
	}

	public void setTradeAreaIds(String tradeAreaIds) {
		this.tradeAreaIds = tradeAreaIds;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getFeatureIds() {
		return featureIds;
	}

	public void setFeatureIds(String featureIds) {
		this.featureIds = featureIds;
	}

	public String getAscSort() {
		return ascSort;
	}

	public void setAscSort(String ascSort) {
		this.ascSort = ascSort;
	}

	public String getDescSort() {
		return descSort;
	}

	public void setDescSort(String descSort) {
		this.descSort = descSort;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
}
