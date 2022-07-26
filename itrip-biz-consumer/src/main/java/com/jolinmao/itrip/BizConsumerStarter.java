package com.jolinmao.itrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <b>爱旅行-主业务模块消费者启动类</b>
 * @author Jolinmao
 * @version 1.0.0
 * @since 2020 03 01
 */
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class BizConsumerStarter {
	public static void main(String[] args) {
		SpringApplication.run(BizConsumerStarter.class,args);
	}
}
