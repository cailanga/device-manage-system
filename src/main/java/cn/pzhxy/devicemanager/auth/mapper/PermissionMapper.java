package cn.pzhxy.devicemanager.auth.mapper;

import cn.pzhxy.devicemanager.auth.domain.Permission;
import cn.pzhxy.devicemanager.auth.query.PermissionQuery;

import java.util.List;

public interface PermissionMapper {
    /**
     * 新增权限
     * @param permission 权限对象
     */
    void insert(Permission permission);

    /**
     * 删除所有权限数据
     */
    void deleteAll();

    /**
     * 查询所有权限
     * @return 权限集合
     */
    List<Permission> selectAll();

    /**
     * 根据查询条件（keyword）查询符合条件的总条数
     * @param query 查询对象
     * @return 符合条件的总条数
     */
    Long queryTotal(PermissionQuery query);

    /**
     * 根据条件进行分页查询
     * @param query 查询条件
     * @return 权限集合
     */
    List<Permission> queryPageList(PermissionQuery query);

    /**
     * 通过员工id获取权限
     * @param employeeId 员工id
     * @return 权限集合
     */
    List<String> querySnsByEmployeeId(Long employeeId);
}
