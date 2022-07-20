package com.jolinmao.itrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <b>爱旅行-搜索业务模块生产者启动类</b>
 * @author Jolinmao
 * @version 1.0.0
 * @since 2020 03 01
 */
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class SearchConsumerStarter {
	public static void main(String[] args) {
		SpringApplication.run(SearchConsumerStarter.class, args);
	}
}

