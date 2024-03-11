package cn.pzhxy.devicemanager.auth.service.impl;

import cn.pzhxy.devicemanager.auth.service.ILoginService;
import cn.pzhxy.devicemanager.base.utils.LoginConstant;
import cn.pzhxy.devicemanager.org.domain.Employee;
import cn.pzhxy.devicemanager.org.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * @ClassName: LoginServiceImpl
 * @Description: 登录
 * @Author: 2026972757@qq.com
 * @Date: 2024/3/4 16:00
 * @Version 1.0
 **/
@Service
public class LoginServiceImpl implements ILoginService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public Map<String, Object> login(Employee employee) {
        if (StringUtils.isEmpty(employee.getPassword())||StringUtils.isEmpty(employee.getUsername())){
            //如果为用户名或密码为空则直接返回
            throw new RuntimeException("用户名或密码不能为空！");
        }
        Employee employee1 = employeeMapper.selectByUsername(employee.getUsername());
        if (Objects.isNull(employee1)){
            //如果为null，则是没有相关信息
            throw new RuntimeException("用户名或密码错误！");
        }
        if (!Objects.equals(employee.getPassword(),employee1.getPassword())){
            //如果密码不相等则是密码错误
            throw new RuntimeException("用户名或密码错误！");
        }
        //员工信息中的密码置空
        employee1.setPassword("");
        //通过uuid生成唯一token
        String token = UUID.randomUUID().toString();
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("employee",employee1);
        //将登录的员工token和员工信息放入常量
        LoginConstant.loginMap.put(token, employee1);
        return map;
    }
}
