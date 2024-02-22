package cn.cailang.org.service;

import cn.cailang.base.service.IBaseService;
import cn.cailang.org.domain.Department;
import java.util.List;

public interface IDepartmentService extends IBaseService<Department> {
    /**
     * 查询所有一级部门及其子部门
     * @return 部门集合
     */
    List<Department> queryFirstDeptAndChildren();
}
