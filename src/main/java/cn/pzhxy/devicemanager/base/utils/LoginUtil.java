package cn.pzhxy.devicemanager.base.utils;

import cn.pzhxy.devicemanager.org.domain.Employee;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: LoginUtil
 * @Description: 登录工具类
 * @Author: 2026972757@qq.com
 * @Date: 2024/3/11 12:27
 * @Version 1.0
 **/
public class LoginUtil {
    /**
     * 获取登录用户信息
     * @param request 请求对象
     * @return 用户对象
     */
    public static Employee getUserInfo(HttpServletRequest request){
        Employee employee;
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)){
            throw new RuntimeException("未登录");
        }
        employee = (Employee) LoginConstant.loginMap.get(token);
        return employee;
    }

    /**
     * 获取登录用户信息
     * @param token 登录用户token
     * @return 用户对象
     */
    public static Employee getUserInfo(String token){
        if (StringUtils.isEmpty(token)){
            throw new RuntimeException("未登录");
        }
        Employee employee = (Employee) LoginConstant.loginMap.get(token);
        return employee;
    }
}
