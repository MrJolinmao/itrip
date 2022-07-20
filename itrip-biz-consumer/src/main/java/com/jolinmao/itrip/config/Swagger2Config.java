package com.jolinmao.itrip.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @auth jolinmao
 * @date 2022 07 17
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    //配置了Swagger的Docket的bean实例
    //enable 是否启动swagger
    @Bean
    public Docket docket(){

        // 设置要显示swagger的环境
        // Profiles of = Profiles.of("dev", "test");
        // 判断当前是否处于该环境
        // 通过 enable() 接收此参数判断是否要显示
        // boolean flag = environment.acceptsProfiles(of);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("jolinmao")
                .enable(true)
                .select()
                //RequestHandlerSelectors,配置需要扫描接口的方式
                //basePackage:指定要扫描的包
                //any():扫描全部
                //none():不扫描
                //withClassAnnotation:扫描类上的注解
                //withClassAnnotation:扫描类上的注解，参数是一个注解的反射对象
                //withMethodAnnotation：扫描方法上的注解
                .apis(RequestHandlerSelectors.basePackage("com.jolinmao.itrip.controller"))
                //paths()：过滤什么路径
                //.paths(PathSelectors.regex("/trans/.*"))
                .build();
    }

    /**
     * 配置swagger信息=apiInfo
     */
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Itrip项目 API文档")
                .description("Itrip项目 API文档")
                // 作者信息
                .contact(new Contact("jolinmao", "", ""))
                .version("0.0.1")
                .build();
    }
}
