package cn.cailang.org.mapper;

import cn.cailang.base.mapper.BaseMapper;
import cn.cailang.org.domain.Department;
import java.util.List;

public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * 查询所有一级部门及其子部门
     * @return 部门集合
     */
    List<Department> queryFirstDeptAndChildren();

}
