package cn.cailang.auth.mapper;

import cn.cailang.auth.domain.Menu;
import cn.cailang.auth.domain.Permission;
import cn.cailang.auth.domain.Role;
import cn.cailang.base.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据名称查询
     * @param name 角色名称
     * @return 角色信息
     */
    Role selectByName(String name);

    /**
     * 根据标识查询
     * @param sn 角色标识
     * @return 角色信息
     */
    Role selectBySn(String sn);

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
    void setRolePermissions(@Param("roleId") Long roleId,@Param("permissionSns") List<String> permissionSns);

    /**
     * 通过roleId删除角色权限
     * @param roleId 角色id
     */
    void deleteRolePermissionByRoleId(Long roleId);

    /**
     * 根据角色Id查询角色权限标识
     * @param roleId 角色id
     * @return 权限标识集合
     */
    List<String> selectPermissionSnsByRoleId(Long roleId);

    /**
     * 通过角色id删除该角色的菜单信息
     * @param roleId 角色id
     */
    void deleteRoleMenuByRoleId(Long roleId);

    /**
     * 设置角色菜单
     * @param roleId 角色id
     * @param menuIds 菜单ids
     */
    void setRoleMenus(@Param("roleId") Long roleId,@Param("menuIds")List<String> menuIds);

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
