package com.dd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 图片资源访问配置
 */
@Configuration
public class ResourceConfig implements WebMvcConfigurer {

    //表示访问file路径，自动映射到磁盘目录上的对应的e://file路径去，就可以访问里面的图片
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/**").addResourceLocations("file:E://file//");
    }
}
