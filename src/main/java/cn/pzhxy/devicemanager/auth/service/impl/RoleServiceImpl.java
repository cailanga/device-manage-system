package cn.pzhxy.devicemanager.auth.service.impl;

import cn.pzhxy.devicemanager.auth.domain.Menu;
import cn.pzhxy.devicemanager.auth.domain.Permission;
import cn.pzhxy.devicemanager.auth.domain.Role;
import cn.pzhxy.devicemanager.auth.mapper.RoleMapper;
import cn.pzhxy.devicemanager.auth.service.IRoleService;
import cn.pzhxy.devicemanager.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName: RoleServiceImpl
 * @Description: 角色业务层
 * @Author: 2026972757@qq.com
 * @Date: 2024/2/27 12:38
 * @Version 1.0
 **/
@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class RoleServiceImpl extends BaseServiceImpl<Role> implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Transactional
    @Override
    public void insert(Role role) {
        //通过name查询是否已经存在
        Role roleByNameOrSn = roleMapper.selectByName(role.getName());
        if(Objects.nonNull(roleByNameOrSn)){
            //不为null，则是已存在
            throw new RuntimeException("角色名称已存在");
        }
        roleByNameOrSn = roleMapper.selectBySn(role.getSn());
        if(Objects.nonNull(roleByNameOrSn)){
            //不为null，则是已存在
            throw new RuntimeException("角色标识已存在");
        }
        roleMapper.insert(role);
    }
    @Transactional
    @Override
    public void update(Role role) {
        //通过name查询是否已经存在
        Role roleByNameOrSn = roleMapper.selectByName(role.getName());
        if(Objects.nonNull(roleByNameOrSn)&&!roleByNameOrSn.getId().equals(role.getId())){
            //不为null且id不相等，则是已存在
            throw new RuntimeException("角色名称已存在");
        }
        roleByNameOrSn = roleMapper.selectBySn(role.getSn());
        if(Objects.nonNull(roleByNameOrSn)&&!roleByNameOrSn.getId().equals(role.getId())){
            //不为null且id不相等，则是已存在
            throw new RuntimeException("角色标识已存在");
        }
        roleMapper.update(role);
    }

    @Override
    public List<Permission> selectPermissionTree() {
        return roleMapper.selectPermissionTree();
    }

    @Transactional
    @Override
    public void setRolePermissions(Long roleId, List<String> permissionSns) {
        //先删除roleId已有的权限
        roleMapper.deleteRolePermissionByRoleId(roleId);
        //然后在添加权限
        if (!permissionSns.isEmpty()){
            roleMapper.setRolePermissions(roleId,permissionSns);
        }
    }

    @Override
    public List<String> selectPermissionSnsByRoleId(Long roleId) {
        return roleMapper.selectPermissionSnsByRoleId(roleId);
    }

    @Override
    public void setRoleMenus(Long roleId, List<String> menuIds) {
        //先删除roleId已有的菜单
        roleMapper.deleteRoleMenuByRoleId(roleId);
        //然后在添加菜单
        if (!menuIds.isEmpty()){
            roleMapper.setRoleMenus(roleId,menuIds);
        }
    }

    @Override
    public List<String> selectMenuIdsByRoleId(Long roleId) {
        return roleMapper.selectMenuIdsByRoleId(roleId);
    }

    @Override
    public List<Menu> selectMenuTree() {
        return roleMapper.selectMenuTree();
    }
}
