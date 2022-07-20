package com.jolinmao.itrip.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @auth jolinmao
 * @date 2022 07 18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "SearchOrderVO", description = "前端输入-查询个人订单搜索条件VO")
public class SearchOrderVO implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("[非必填] 订单号")
    private String orderNo;

    @ApiModelProperty("[非必填] 联系人")
    private String linkUserName;

    @ApiModelProperty("[非必填] 预定时间（start）")
    private Date startDate;

    @ApiModelProperty("[非必填] 预定时间（end）")
    private Date endDate;

    @ApiModelProperty("[必填] 页面容量")
    private Integer pageSize;

    @ApiModelProperty("[必填] 页码")
    private Integer pageNo;

    @ApiModelProperty("[必填，注：接收数字类型] 订单状态（-1：全部订单 0:待付款 1:已取消 2：未出行 3:待评论 ）")
    private Integer orderStatus;

    @ApiModelProperty("[必填，注：接收数字类型] 订单类型（-1：全部订单 0:旅游订单 1:酒店订单 2：机票订单 ）")
    private Integer orderType;
}
