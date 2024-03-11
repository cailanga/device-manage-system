package cn.pzhxy.devicemanager.org.mapper;

import cn.pzhxy.devicemanager.base.mapper.BaseMapper;
import cn.pzhxy.devicemanager.org.domain.Department;

import java.util.List;

public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * 查询所有一级部门及其子部门
     * @return 部门集合
     */
    List<Department> queryFirstDeptAndChildren();

}
