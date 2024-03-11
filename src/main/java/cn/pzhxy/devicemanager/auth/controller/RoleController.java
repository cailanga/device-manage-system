package cn.pzhxy.devicemanager.auth.controller;

import cn.pzhxy.devicemanager.auth.annotation.JiaXinPermission;
import cn.pzhxy.devicemanager.auth.domain.Menu;
import cn.pzhxy.devicemanager.auth.domain.Permission;
import cn.pzhxy.devicemanager.auth.domain.Role;
import cn.pzhxy.devicemanager.auth.query.RoleQuery;
import cn.pzhxy.devicemanager.auth.service.IRoleService;
import cn.pzhxy.devicemanager.base.utils.AjaxResult;
import cn.pzhxy.devicemanager.base.utils.PageList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName: RoleController
 * @Description: 角色控制层
 * @Author: 2026972757@qq.com
 * @Date: 2024/2/23 16:13
 * @Version 1.0
 **/
@JiaXinPermission(name = "角色管理权限",description = "角色管理权限")
@Api(value = "角色管理",description="角色相关的CRUD功能")
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    /**
     * 通过id查询角色信息
     * @param id 角色id
     * @return AjaxResult对象
     */
    @ApiOperation("通过id查询角色信息")
    @ApiImplicitParam(name = "id", value = "角色ID", required = true, dataType = "Long",paramType = "path")
    @GetMapping("/{id}")
//    @JiaXinPermission(name = "通过id查询角色信息",description = "通过id查询角色信息")
    public AjaxResult selectById(
            @ApiParam(name = "主键",required = true)
            @PathVariable("id") Long id) {
        try {
            Role role = roleService.selectById(id);
            return AjaxResult.me().setResultObject(role);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("操作失败："+e.getMessage());
        }
    }

    /**
     * 查询所有角色信息
     * @return AjaxResult对象
     */
    @ApiOperation("查询所有角色信息")
    @GetMapping
//    @JiaXinPermission(name = "查询所有角色信息权限",description = "查询所有角色信息权限")
    public AjaxResult selectAll() {
        try {
            List<Role> roles = roleService.selectAll();
            return AjaxResult.me().setResultObject(roles);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("操作失败："+e.getMessage());
        }
    }

    /**
     * 根据id删除角色信息
     * @param id 角色id
     * @return AjaxResult对象
     */
    @ApiOperation("删除角色信息")
    @ApiImplicitParam(name = "id", value = "角色ID", required = true, dataType = "Long",paramType = "path")
    @DeleteMapping("/{id}")
    @JiaXinPermission(name = "删除角色信息权限",description = "删除角色信息权限")
    public AjaxResult deleteById(
            @ApiParam(name = "主键",required = true)
            @PathVariable("id") Long id){
        try {
            roleService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("操作失败："+e.getMessage());
        }
    }

    @ApiOperation("批量删除角色信息")
    @PatchMapping
    @JiaXinPermission(name = "批量删除角色信息权限",description = "批量删除角色信息权限")
    public AjaxResult batchDelete(
            @ApiParam(name = "id集合",required = true)
             @RequestBody List<Long> ids){
        try {
            roleService.batchDelete(ids);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("操作失败："+e.getMessage()).setSuccess(false);
        }
    }

    /**
     * 新增或修改角色信息
     * @param role 角色信息
     * @return AjaxResult对象
     */
    @ApiOperation("新增或修改角色信息")
    @PutMapping
    @JiaXinPermission(name = "新增或修改角色信息权限",description = "新增或修改角色信息权限")
    public AjaxResult addOrUpdate(@RequestBody Role role){
        try {
            if (Objects.nonNull(role.getId())){
                //id不为空，则是修改操作
                roleService.update(role);
            }else {
                //新增操作
                roleService.insert(role);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("操作失败："+e.getMessage());
        }
    }

    /**
     * 根据关键字进行分页查询
     * @param query 查询条件对象
     * @return AjaxResult对象
     */
    @ApiOperation("根据关键字进行分页查询")
    @PostMapping
    @JiaXinPermission(name = "根据关键字进行分页查询权限",description = "根据关键字进行分页查询权限")
    public AjaxResult queryDataByKeyword(@RequestBody RoleQuery query){
        try {
            PageList<Role> pageList = roleService.pageList(query);
            return AjaxResult.me().setResultObject(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("操作失败："+e.getMessage());
        }
    }

    @ApiOperation("获取权限树")
    @JiaXinPermission(name = "获取权限树权限")
    @GetMapping("/getPermissionTree")
    public AjaxResult getPermissionTree(){
        try {
            List<Permission> permissions = roleService.selectPermissionTree();
            return AjaxResult.me().setResultObject(permissions);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取权限树失败："+e.getMessage());
        }
    }

    @ApiOperation("设置权限")
    @JiaXinPermission(name = "设置权限")
    @PutMapping("/setPermission")
    public AjaxResult setPermission(@RequestBody Map map){
        try {
            Long roleId = Long.parseLong(map.get("roleId").toString());
            List<String> permissionSns = (List<String>)map.get("permissionSns");
            roleService.setRolePermissions(roleId, permissionSns);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("设置权限失败："+e.getMessage());
        }
    }
    @ApiOperation("通过角色id获取权限标识")
//    @JiaXinPermission(name = "通过角色id获取权限标识")
    @GetMapping("/getPermissionsSnsByRoleId/{roleId}")
    public AjaxResult getPermissionsSnsByRoleId(@PathVariable("roleId") Long roleId){
        try {
            List<String> sns = roleService.selectPermissionSnsByRoleId(roleId);
            return AjaxResult.me().setResultObject(sns);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取标识失败："+e.getMessage());
        }
    }

    @ApiOperation("获取菜单树")
//    @JiaXinPermission(name = "获取菜单树")
    @GetMapping("/getMenuTree")
    public AjaxResult getMenuTree(){
        try {
            List<Menu> menus = roleService.selectMenuTree();
            return AjaxResult.me().setResultObject(menus);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取菜单树失败："+e.getMessage());
        }
    }

    @ApiOperation("设置菜单")
    @JiaXinPermission(name = "设置菜单")
    @PutMapping("/setMenu")
    public AjaxResult setMenu(@RequestBody Map map){
        try {
            Long roleId = Long.parseLong(map.get("roleId").toString());
            List<String> menuIds = (List<String>)map.get("menuIds");
            roleService.setRoleMenus(roleId, menuIds);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("设置菜单失败："+e.getMessage());
        }
    }
    @ApiOperation("通过角色id获取菜单ids")
//    @JiaXinPermission(name = "通过角色id获取菜单ids")
    @GetMapping("/getMenuIdsByRoleId/{roleId}")
    public AjaxResult getMenuIdsByRoleId(@PathVariable("roleId") Long roleId){
        try {
            List<String> sns = roleService.selectMenuIdsByRoleId(roleId);
            return AjaxResult.me().setResultObject(sns);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取菜单ids失败："+e.getMessage());
        }
    }
}
