package com.jolinmao.itrip.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <b>返回前端房间库存信息vo对象</b>
 * @auth jolinmao
 * @date 2022 07 02
 */
@ApiModel(value = "RoomStoreVO", description = "返回前端-房间库存对象")
public class RoomStoreVO implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "酒店id")
	private Long hotelId;
	@ApiModelProperty(value = "房间id")
	private Long roomId;
	@ApiModelProperty(value = "入住时间")
	private Date checkInDate;
	@ApiModelProperty(value = "离开时间")
	private Date checkOutDate;
	@ApiModelProperty(value = "房间数量")
	private Integer count;
	@ApiModelProperty(value = "酒店名称")
	private String hotelName;
	@ApiModelProperty(value = "房间剩余库存")
	private Integer store;
	@ApiModelProperty(value = "价格")
	private Double price;

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public Integer getStore() {
		return store;
	}

	public void setStore(Integer store) {
		this.store = store;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
