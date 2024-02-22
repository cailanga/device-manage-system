package cn.cailang.auth.service;

import cn.cailang.org.domain.Employee;

import java.util.Map;

public interface ILoginService {

    /**
     * 员工登录
     * @param employee 员工信息
     * @return 员工信息，以及token
     */
    Map<String,Object> login(Employee employee);
}
