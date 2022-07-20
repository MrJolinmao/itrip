package com.jolinmao.itrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * <b>爱旅行注册中心启动类</b>
 * @author JOLINMao
 * @version 1.0.0
 * @since 2020 03 03
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerStarter {
	public static void main(String[] args) {
		SpringApplication.run(EurekaServerStarter.class,args);
	}
}
