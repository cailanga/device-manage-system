package cn.pzhxy.devicemanager.org.service.impl;

import cn.pzhxy.devicemanager.base.service.impl.BaseServiceImpl;
import cn.pzhxy.devicemanager.org.domain.Employee;
import cn.pzhxy.devicemanager.org.mapper.EmployeeMapper;
import cn.pzhxy.devicemanager.org.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

/**
 * @ClassName: EmployeeServiceImpl
 * @Description: 员工业务层
 * @Author: 2026972757@qq.com
 * @Date: 2024/2/23 15:00
 * @Version 1.0
 **/
@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements IEmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Transactional
    @Override
    public void setEmployeeRole(Long employeeId, List<Long> roleIds) {
        //设置员工角色信息时，先删除已存在的员工角色信息
        employeeMapper.deleteEmpRoleByEmployeeId(employeeId);
        if (!roleIds.isEmpty()){
            //如果有角色需要设置
            employeeMapper.setEmployeeRole(employeeId, roleIds);
        }

    }

    @Override
    public List<Long> getEmpRolesByEmplId(Long employeeId) {
        return employeeMapper.getEmpRolesByEmployeeId(employeeId);
    }

}
