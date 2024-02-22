package cn.cailang;

import cn.cailang.base.interceptors.AuthPermInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName: PlatformSystemAppStart
 * @Description: 启动类
 * @Author: 3299903308@qq.com
 * @Date: 2023/4/23 12:21
 * @Version 1.0
 **/
@SpringBootApplication
@MapperScan("cn.cailang.*.mapper")
//扫描实现servlet的监听器包
//@ServletComponentScan("cn.cailang.auth.listener")
public class PlatformSystemAppStart implements WebMvcConfigurer {
    @Autowired
    private AuthPermInterceptor authPermInterceptor;
    public static void main(String[] args) {
        SpringApplication.run(PlatformSystemAppStart.class,args);
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
