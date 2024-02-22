package cn.cailang.auth.service;

import cn.cailang.auth.domain.Menu;
import cn.cailang.auth.domain.Permission;
import cn.cailang.auth.domain.Role;
import cn.cailang.base.service.IBaseService;
import java.util.List;

public interface IRoleService extends IBaseService<Role> {

    /**
     * 获取权限树
     * @return 权限数据
     */
    List<Permission> selectPermissionTree();

    /**
     * 设置角色权限
     * @param roleId 角色id
     * @param permissionSns 权限sn标识
     */
    void setRolePermissions(Long roleId,List<String> permissionSns);

    /**
     * 根据角色Id查询角色权限标识
     * @param roleId 角色id
     * @return 权限标识集合
     */
    List<String> selectPermissionSnsByRoleId(Long roleId);

    /**
     * 给角色设置菜单
     * @param roleId 角色id
     * @param menuIds 菜单id集合
     */
    void setRoleMenus(Long roleId, List<String> menuIds);

    /**
     * 通过角色id获取该角色的菜单的ids
     * @param roleId 角色id
     * @return 菜单ids
     */
    List<String> selectMenuIdsByRoleId(Long roleId);

    /**
     * 获取菜单树
     * @return 菜单集合
     */
    List<Menu> selectMenuTree();
}
