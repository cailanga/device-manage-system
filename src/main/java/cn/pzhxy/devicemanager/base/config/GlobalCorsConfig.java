package cn.pzhxy.devicemanager.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class GlobalCorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        //1.添加CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
        //1) 允许的域,不要写*，否则cookie就无法使用了
        config.addAllowedOrigin("http://127.0.0.1:8081");
        config.addAllowedOrigin("http://localhost:8081");
        config.addAllowedOrigin("http://127.0.0.1:8080");
        config.addAllowedOrigin("http://localhost:8080");
        config.addAllowedOrigin("http://127.0.0.1:9000");
        config.addAllowedOrigin("http://localhost:9000");
        config.addAllowedOrigin("http://47.109.101.54:8080");
        config.addAllowedOrigin("http://127.0.0.1:80");
        config.addAllowedOrigin("http://192.168.1.190:8080");
        config.addAllowedOrigin("http://192.168.1.190:9000");
        config.addAllowedOrigin("http://localhost:80");
        config.addAllowedOrigin("http://127.0.0.1");
        config.addAllowedOrigin("http://localhost");


        //2) 是否发送Cookie信息
        config.setAllowCredentials(true);
        //3) 允许的请求方式
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        // 4）允许的头信息
        config.addAllowedHeader("*");
        //2.添加映射路径，我们拦截一切请求
        UrlBasedCorsConfigurationSource configSource = new
                UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        //3.返回新的CorsFilter.
        return new CorsFilter(configSource);
    }
}