package cn.pzhxy.devicemanager;

import cn.pzhxy.devicemanager.base.interceptors.AuthPermInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName: PlatformSystemAppStart
 * @Description: 启动类
 * @Author: 2026972757@qq.com
 * @Date: 2024/2/23 12:21
 * @Version 1.0
 **/
@SpringBootApplication
@MapperScan("cn.pzhxy.devicemanager.*.mapper")
@EnableScheduling //开启定时任务
//扫描实现servlet的监听器包
//@ServletComponentScan("cn.pzhxy.devicemanager.auth.listener")
public class PlatformSystemAppStart implements WebMvcConfigurer {
    @Autowired
    private AuthPermInterceptor authPermInterceptor;
    public static ConfigurableApplicationContext context = null;
    public static void main(String[] args) {

        context = SpringApplication.run(PlatformSystemAppStart.class,args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 不拦截登录和注销
        registry.addInterceptor(authPermInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login","/logout")
                .excludePathPatterns("/upload/headImage/**") //头像资源放行
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");//swagger资源放行
    }
}
