package cn.cailang.auth.controller;

import cn.cailang.auth.service.ILoginService;
import cn.cailang.base.utils.AjaxResult;
import cn.cailang.base.utils.LoginConstant;
import cn.cailang.org.domain.Employee;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @ClassName: LoginController
 * @Description: 登录
 * @Author: 3299903308@qq.com
 * @Date: 2023/5/4 15:58
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
