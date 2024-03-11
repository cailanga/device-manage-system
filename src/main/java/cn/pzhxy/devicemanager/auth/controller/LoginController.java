package cn.pzhxy.devicemanager.auth.controller;

import cn.pzhxy.devicemanager.auth.service.ILoginService;
import cn.pzhxy.devicemanager.base.utils.AjaxResult;
import cn.pzhxy.devicemanager.base.utils.LoginConstant;
import cn.pzhxy.devicemanager.org.domain.Employee;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @ClassName: LoginController
 * @Description: 登录
 * @Author: 2026972757@qq.com
 * @Date: 2024/2/4 15:58
 * @Version 1.0
 **/
@RestController
public class LoginController {
    @Autowired
    private ILoginService loginService;
    /**
     * 登录
     * @param employee 员工登录信息
     * @return AjaxResult
     */
    @ApiOperation("登录")
    @PostMapping("/login")
    public AjaxResult login(@RequestBody Employee employee){
        try {
            Map map = loginService.login(employee);
            return AjaxResult.me().setResultObject(map);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage(e.getMessage());
        }
    }

    /**
     * 登出
     * @return AjaxResult
     */
    @ApiOperation("登录")
    @PostMapping("/logout")
    public AjaxResult logout(HttpServletRequest request){
        try {
            //获取token
            String token = request.getHeader("token");
            //移除token
            LoginConstant.loginMap.remove(token);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage(e.getMessage());
        }
    }
}
