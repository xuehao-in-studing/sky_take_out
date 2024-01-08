package com.sky.config;

import com.sky.interceptor.JwtTokenAdminInterceptor;
import com.sky.json.JacksonObjectMapper;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 配置类，注册web层相关组件
 * 打开api文档的地质: http:localhost:8080/doc.html
 * 其中localhost代表ip，8080代表端口号，可以更换
 */
@Configuration
@Slf4j
public class WebMvcConfiguration extends WebMvcConfigurationSupport {


    @Autowired
    private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;

    /**
     * 注册自定义拦截器
     *
     * @param registry
     */
    protected void addInterceptors(InterceptorRegistry registry) {
        log.info("开始注册自定义拦截器...");
        registry.addInterceptor(jwtTokenAdminInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/employee/login","/doc.html");
    }

    /**
     * 设置静态资源映射
     * @param registry
     */
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 根据@Tag 上的排序，写入x-order
     *
     * @return the global open api customizer
     */
//    @Bean
//    public GlobalOpenApiCustomizer orderGlobalOpenApiCustomizer() {
//        return openApi -> {
//            if (openApi.getTags()!=null){
//                openApi.getTags().forEach(tag -> {
//                    Map<String,Object> map=new HashMap<>();
//                    map.put("x-order", 1);
//                    tag.setExtensions(map);
//                });
//            }
//            if(openApi.getPaths()!=null){
//                openApi.addExtension("x-test123","333");
//                openApi.getPaths().addExtension("x-abb",1);
//            }
//
//        };
//    }
//
//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .info(new Info()
//                        .title("苍穹外卖接口文档")
//                        .version("1.0")
//                        .description( "苍穹外卖接口文档")
//                        .termsOfService("http://doc.xiaominfo.com")
//                        .license(new License().name("Apache 2.0")
//                                .url("http://doc.xiaominfo.com")));
//    }

    /**
     * 扩展消息转换器,将日期类型从列表转换为时间戳
     * @param converters 消息转换器列表
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        jackson2HttpMessageConverter.setObjectMapper(new JacksonObjectMapper());

        converters.add(0,jackson2HttpMessageConverter);

    }
}
