package cn.cailang.org.service.impl;

import cn.cailang.base.service.impl.BaseServiceImpl;
import cn.cailang.org.domain.Department;
import cn.cailang.org.mapper.DepartmentMapper;
import cn.cailang.org.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName: DepartmentServiceImpl
 * @Description: 部门业务层
 * @Author: 3299903308@qq.com
 * @Date: 2023/4/23 15:00
 * @Version 1.0
 **/
@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements IDepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Transactional
    @Override
    public void insert(Department department) {
        //创建时间
        department.setCreateTime(new Date());
        //生成path
        String path = "";
        Department parent = department.getParent();
        if (Objects.nonNull(parent)&& Objects.nonNull(parent.getId())) {
            //此情况不为一级部门,查询父级部门
            parent = departmentMapper.selectById(parent.getId());
            //获取父级部门的path
            path = parent.getPath();
        }
        departmentMapper.insert(department);
        path=path+"/"+department.getId();
        department.setPath(path);
        departmentMapper.update(department);
    }

    @Transactional
    @Override
    public void update(Department department) {
        //修改时间
        department.setUpdateTime(new Date());
        //生成path
        String path = "";
        Department parent = department.getParent();
        if (Objects.nonNull(parent)&& Objects.nonNull(parent.getId())) {
            //此情况不为一级部门,查询父级部门
            parent = departmentMapper.selectById(parent.getId());
            //获取父级部门的path
            path = parent.getPath();
        }
        path=path+"/"+department.getId();
        department.setPath(path);
        departmentMapper.update(department);
    }

    @Override
    public List<Department> queryFirstDeptAndChildren() {
        return departmentMapper.queryFirstDeptAndChildren();
    }
}
