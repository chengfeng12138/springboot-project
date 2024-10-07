package com.chengfeng.study.myspringbootproject.config.mvcconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * MyMvcConfig class
 * 自定义配置类
 * 视图控制
 * @author chengfeng
 * @date 2021/7/17 /0017 18:46
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addExposedHeader("Authorization");
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }

    /**
    * 跨域处理
    * @author chengfeng
    * @date 2021/8/15 /0015 14:55
    **/
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
    }

    //private static final String[] EXCLUDE_PATH_STRS = {"/index.html", "/",
    //        "/action/login", "/getLoginQr", "/phoneLogin", "/upload"};
    //
    //@Override
    //public void addViewControllers(ViewControllerRegistry registry) {
    //    //视图控制 首页
    //    registry.addViewController("/").setViewName("index");
    //    registry.addViewController("/index.html").setViewName("index");
    //}
    //
    //@Override
    //public void addInterceptors(InterceptorRegistry registry) {
    //
    //    //拦截器 拦截请求, 排除不需要拦截的请求, 首页，登录，静态资源等
    //    registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns(EXCLUDE_PATH_STRS);
    //}

}
