package com.chengfeng.study.myspringbootproject.config.swaggerconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * SwaggerConfig class
 * Swagger配置类
 *        访问地址: http://localhost:8080/swagger-ui/index.html#/
 *        实体类: 只要扫描的包下返回了实体类, swagger文档就会显示该实体类
 * @author chengfeng
 * @date 2021/8/14 /0014 17:46
 */
@Configuration
public class SwaggerConfig {

    //分组1
    @Bean
    public Docket createRestApi(Environment environment) {
        //获取项目环境
        Profiles profiles = Profiles.of("dev");
        //是否为dev测试环境(根据配置文件)
        boolean isDev = environment.acceptsProfiles(profiles);
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .groupName("分组1")
                .enable(isDev) //是否启用swagger访问
                .select()
                //RequestHandlerSelectors 配置扫描接口的方式
                .apis(RequestHandlerSelectors.basePackage("com.chengfeng.study.myspringbootproject.controller"))
                //过滤路径
                .paths(PathSelectors.any())
                .build();
    }

    //分组2
    @Bean
    public Docket createRestApi2() {
        return new Docket(DocumentationType.OAS_30).groupName("分组2");
    }

    //分组3
    @Bean
    public Docket createRestApi3() {
        return new Docket(DocumentationType.OAS_30).groupName("分组3");
    }

    /**
    * 配置文档信息
    * @author chengfeng
    * @date 2021/8/14 /0014 18:39
    **/
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("程峰的Swagger3接口文档")
                .description("时光不负有心人~")
                .contact(new Contact("chengfeng", "#", "1210714514@qq.com"))
                .version("v1.0")
                .build();
    }
}
