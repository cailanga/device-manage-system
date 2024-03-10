package cn.cailang.base.interceptors;

import cn.cailang.auth.annotation.JiaXinPermission;
import cn.cailang.base.utils.AjaxResult;
import cn.cailang.base.utils.LoginConstant;
import cn.cailang.org.domain.Employee;
import cn.cailang.org.mapper.EmployeeMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName: AuthPermInterceptor
 * @Description: 认证授权拦截器
 * @Author: 3299903308@qq.com
 * @Date: 2023/5/4 11:04
 * @Version 1.0
 **/
@Component
public class AuthPermInterceptor implements HandlerInterceptor {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取前端携带的token
        String token = request.getHeader("token");
        //对象转json字符串对象
        ObjectMapper objectMapper = new ObjectMapper();
        if(StringUtils.isEmpty(token)){
            //如果为空则是权限认证失败
            String ajaxResult = objectMapper.writeValueAsString(AjaxResult.me().setSuccess(false).setMessage("noLogin"));
            response.getWriter().print(ajaxResult);
            //不放行
            return false;
        }
        // 从登录常量获取登录相关信息
        Object loginUser = LoginConstant.loginMap.get(token);
        if (Objects.isNull(loginUser)) {
            //如果为空则是权限认证失败
            String ajaxResult = objectMapper.writeValueAsString(AjaxResult.me().setSuccess(false).setMessage("noLogin"));
            response.getWriter().print(ajaxResult);
            //不放行
            return false;
        }
        Employee employee = (Employee) loginUser;
        if (handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            JiaXinPermission annotation = handlerMethod.getMethod().getAnnotation(JiaXinPermission.class);
            if(Objects.isNull(annotation)){
                //如果为空，则说明当前访问资源不需要权限,直接放行
                return true;
            }
            //获取所在类名
            String className = handlerMethod.getBeanType().getSimpleName();
            //获取方法名
            String methodName = handlerMethod.getMethod().getName();
            //当前访问资源的权限
            String sn = className+":"+methodName;
            //获取当前登录用户拥有的权限
            List<String> sns = employeeMapper.selectSnsByEmployeeId(employee.getId());
            if (!sns.contains(sn)){
                //如果不包含，则当前用户不拥有访问当前资源的权限
                String ajaxResult = objectMapper.writeValueAsString(AjaxResult.me().setSuccess(false).setMessage("noPermission"));
                response.getWriter().print(ajaxResult);
                //不放行
                return false;
            }
        }
        return true;
    }
}
