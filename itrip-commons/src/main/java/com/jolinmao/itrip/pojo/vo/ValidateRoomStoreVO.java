package com.jolinmao.itrip.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <b>验证房屋库存是否存足的VO</b>
 * @auth jolinmao
 * @date 2022 07 02
 */
@ApiModel(description = "接收前端-查验房间库存视图对象")
public class ValidateRoomStoreVO implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "酒店id", required = true)
	private Long hotelId;
	@ApiModelProperty(value = "房间id", required = true)
	private Long roomId;
	@ApiModelProperty(value = "入住时间", required = true)
	private Date checkInDate;
	@ApiModelProperty(value = "离开时间", required = true)
	private Date checkOutDate;
	@ApiModelProperty(value = "房间数量", required = true)
	private Integer count;

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
}
