package com.jolinmao.itrip.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayOpenPublicTemplateMessageIndustryModifyRequest;
import com.jolinmao.itrip.base.controller.BaseController;
import com.jolinmao.itrip.base.enums.OrderStatusEnum;
import com.jolinmao.itrip.base.pojo.vo.ResponseDto;
import com.jolinmao.itrip.pojo.entity.HotelOrder;
import com.jolinmao.itrip.pojo.entity.HotelRoom;
import com.jolinmao.itrip.transport.HotelOrderTransport;
import com.jolinmao.itrip.transport.HotelRoomTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auth jolinmao
 * @date 2022 07 03
 */
@RestController("tradeController")
@RequestMapping("/trade/api")
public class TradeController extends BaseController {
	@Autowired
	private HotelOrderTransport hotelOrderTransport;
	@Autowired
	private HotelRoomTransport hotelRoomTransport;

	@GetMapping(value = "/prepay/{orderNo}")
	public ResponseDto<Object> prePay(@PathVariable("orderNo") String orderNo) throws Exception {
		// 通过订单编号查找对应的订单信息
		HotelOrder queryOrder = new HotelOrder();
		queryOrder.setOrderNo(orderNo);
		HotelOrder hotelOrder = hotelOrderTransport.getHotelOrderByOrderNo(queryOrder);
		if (hotelOrder != null && hotelOrder.getOrderStatus() == OrderStatusEnum.ORDER_STATUS_PREPAY.getCode()) {
			// 查询房间信息
			HotelRoom hotelRoom = hotelRoomTransport.getHotelRoomById(hotelOrder.getRoomId());
			// 该订单可以进行支付
			// 获得订单信息
			String out_trade_no = hotelOrder.getTradeNo();
			Double total_amount = hotelOrder.getPayAmount();
			String subject = hotelOrder.getHotelName();
			String body = hotelOrder.getHotelName() + hotelRoom.getRoomTitle() + "," + hotelOrder.getCount() + "间," + hotelOrder.getBookingDays() + "天";

			//实例化客户端
            AlipayClient alipayClient = new DefaultAlipayClient(
                    "https://openapi.alipaydev.com/gateway.do",
                    "2016102300746619",
                    "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCAhqghOuuy+n/oUryPDi6Jtsx92YYSMn5pOy2q7AZ0Gr4JxPtMf5rhtrxZGtMLUuBIQtUa+P5+S4HOhUEYMi2LUE4rDytaZIH0MDWQ6QqwIK5FU3bZfw4S4Y/5Lu4xwti2ycQyu1XqDRjdvQ4GAykndjrYpgCubJK2WS1/dBO2n3mfNpiDDGEqyzMs+4s5N4ecOPaLGoljmk1VC6D38zSODM8eU0xt5CiKRIGVVFWaxSu21P2d+Iec3rxviwbVBuFU0RFdxNbWfdj5q+Pz015/uySeH/oEPuwgtuuZMKQJt2kSGYW7xWILtZ3dx6Xk5My9qjBtkrnt2FzVA4G3Dm4XAgMBAAECggEAaTXRy+H/r7N/oR6ZXEGvtuv82ciHB75t3Jak+5XacpCxoC2vyR9sGfkMlaRvvoVJM94BtOK+8YO97uEGOFhbD//c/UKdqpE8EDimoMkkBqm5/uYkFEb9rlcXFFRmiDWIUZk5Eo5n9gBiqqFoKWPM/JC7+z1osWAPeVXm+L58pLEcDH2L+z3cS8excDxO0FjY8SU16rrMF8Vn2as3g+rnEXMwR0Cq+Aeh2FM3+WF9P36sPbuVcJxYHY1E/uvkOVIkXiadB1gc5wa4gKBipwQbE8RX5lgZH6YIBKACuS1aR317OB/yNP6jt0dqV5+xCQkj6fQaOgTD8AhTbarirVklAQKBgQDNUWCvq0wskXnfS8dgdkGbZEbNd8znFkN+du9LII+cblq6hOBLiZUFIKWiEbvmSxuLXzVMr7oka6X2IuTGVkJFazQpSaSjux0StGjHlJDSfBgIpHMHuuo+mcCgaMIyjt3ofRgAmatKdgBGUVh7v+jX5GHsyZOUmHn+Z/YuLx5j2QKBgQCgQJTRvkeJh6lyw3Auq93UC13bH3B+QLv5F+SEpdOfeIR91enefjem4v4A0ZRbTYtV/rcxEDXyh4wi2m0goe9MqQuPXsVmV3KkfS8Xc07Au/KcHXvHpX0bRFjTbeR2knsfDpPVM0bKmKIEj6tc850lFj9PtnpiPM1JyHNT35tbbwKBgQC3gCzGtHVPO/HNhX+7EdYPGK9DAfhq7HYEOC4kWgSgTeze2NVSDbPXMld2oxzpdWU3yi7ry/60gFWCx6rqLLvsKSUkZYI1J42TYxRmk7xfsrMUPk3kscSbkHUdqKMv5ookOHFUUANMeGxEbH2FlH55Pc/bfkldPG2D18ypeElT+QKBgEsJUmXPwjF8q3RIXRwNb8ZdOCplU0hayWm+rUsLVsciKG+/nU2ILkjfmu4JNH14FtlCdsbrf0c7/vtG6bhQ+vsbYIVWa/oa0lyBHW7OmD6ZAuXkzFPUgnslCq6dk0+OEUaejcxTwT8WLy8RkrzoeVmnTCfoGvAq5jk5Of85AcbTAoGBALlsKE5XNCt9Mc82HcH3nrHDa+vaH7gnoBX3Fu9eIQIjyIa9XnVmKZYqgJmW85KHck6LXesIPhvcbWTocswSJRev9ZgN5qMO0BAe1vNzxtSg+QCYvIOxkUp7ach9uZIoLec4E4RIZ+i+evQvjkgkLpvQ8JwrTxU5Cnyhtly4hw8k",
                    "json",
                    "UTF-8",
                    "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAq4uK6lqIT2Iq1Mlg9oDklgmPWiv5H5UMTwKyySSecqELyhXZkaiSasnNvRumQLTuL7SQEBbmc6RN5jBD9VqKg20TuWfPh3oKn288VqE6lLJICsGLiR8HbxHDdzVRGvp0UqNVHHSAKH0MV6iG3mODgRvuHS7Pz05hW1TqmHCk3BTT67a1F93XrOkdb+pNtcWCOpXVa9lCNIC4BtYCgqoJBVIYQBCOJ4z8N6XdDf2sjNjigxo1MFUwqEY0Kj+50iJk5St8iInQLE2I+DgPCX6N+Yg+azCIT4Z7gsjXHpy8LQWrZV49Ovpn4ZTc+pmVXRgtSM58Lx1mihzuyQE4F9Rk0wIDAQAB",
                    "RSA2");
            //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.open.public.template.message.industry.modify
			AlipayOpenPublicTemplateMessageIndustryModifyRequest request = new AlipayOpenPublicTemplateMessageIndustryModifyRequest();
            request.setReturnUrl("http://localhost/itrip");
            request.setNotifyUrl("http://itrip.project.bdqn.cn/trade/api/notify/" + hotelOrder.getId());//在公共参数中设置回跳和通知地址
            //SDK已经封装掉了公共参数，这里只需要传入业务参数
            //此次只是参数展示，未进行字符串转义，实际情况下请转义
            String json = "{\"out_trade_no\":\"" + out_trade_no
                    + "\", \"product_code\":\"FAST_INSTANT_TRADE_PAY\", "
                    + "\"total_amount\":\"" + total_amount
                    + "\",\"subject\":\"" + subject
                    + "\",\"body\":\"" + body + "\","
                    + "\"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\",\"extend_params\":{}}";

            request.setBizContent(json);//填充业务参数

            String form="";
            try {
                form = alipayClient.pageExecute(request).getBody(); //调用SDK生成表单
            } catch (AlipayApiException e) {
                e.printStackTrace();
            }
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(form);//直接将完整的表单html输出到页面
            response.getWriter().flush();
            response.getWriter().close();

		}
		return ResponseDto.failure();
	}
}
