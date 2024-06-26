package cn.pzhxy.devicemanager.org.service;

import cn.pzhxy.devicemanager.base.service.IBaseService;
import cn.pzhxy.devicemanager.org.domain.Employee;

import java.util.List;

public interface IEmployeeService extends IBaseService<Employee> {

    /**
     * 设置角色权限
     * @param employeeId 员工id
     * @param roleIds 角色id集合
     */
    void setEmployeeRole(Long employeeId, List<Long> roleIds);

    /**
     * 根据角色id获取其角色的ids
     * @param employeeId 员工id
     * @return ids集合
     */
    List<Long> getEmpRolesByEmplId(Long employeeId);

}
