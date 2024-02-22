package cn.cailang.auth.service;

import cn.cailang.auth.domain.Permission;
import cn.cailang.auth.query.PermissionQuery;
import cn.cailang.base.utils.PageList;

import java.lang.reflect.Method;
import java.util.List;

public interface IPermissionService {

    /**
     * 新增权限
     * @param permission 权限对象
     */
    void insert(Permission permission);

    /**
     * 获取方法的url
     * @param method 方法对象
     * @return 方法的url
     */
    String getMethodUrl(Method method);

    /**
     * 删除所有权限数据
     */
    void deleteAll();

    /**
     * 根据条件进行分页查询
     * @param query 查询条件
     * @return 查询数据
     */
    PageList<Permission> pageList(PermissionQuery query);

    /**
     * 通过员工id获取权限
     * @param employeeId 员工id
     * @return 权限集合
     */
    List<String> getSnsByEmployeeId(Long employeeId);
}
