package cn.cailang.auth.controller;

import cn.cailang.auth.annotation.JiaXinPermission;
import cn.cailang.auth.domain.Menu;
import cn.cailang.auth.query.MenuQuery;
import cn.cailang.auth.service.IMenuService;
import cn.cailang.base.utils.AjaxResult;
import cn.cailang.base.utils.PageList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName: MenuController
 * @Description: 菜单控制层
 * @Author: 3299903308@qq.com
 * @Date: 2023/4/23 16:13
 * @Version 1.0
 **/
@JiaXinPermission(name = "菜单管理权限",description = "菜单管理权限")
@Api(value = "菜单管理",description="菜单相关的CRUD功能")
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private IMenuService menuService;

    /**
     * 通过id查询菜单信息
     * @param id 菜单id
     * @return AjaxResult对象
     */
    @ApiOperation("通过id查询菜单信息")
    @ApiImplicitParam(name = "id", value = "菜单ID", required = true, dataType = "Long",paramType = "path")
    @GetMapping("/{id}")
//    @JiaXinPermission(name = "通过id查询菜单信息",description = "通过id查询菜单信息")
    public AjaxResult selectById(
            @ApiParam(name = "主键",required = true)
            @PathVariable("id") Long id) {
        try {
            Menu menu = menuService.selectById(id);
            return AjaxResult.me().setResultObject(menu);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("操作失败："+e.getMessage());
        }
    }

    /**
     * 查询所有菜单信息
     * @return AjaxResult对象
     */
    @ApiOperation("查询所有菜单信息")
    @GetMapping
//    @JiaXinPermission(name = "查询所有菜单信息权限",description = "查询所有菜单信息权限")
    public AjaxResult selectAll() {
        try {
            List<Menu> menus = menuService.selectAll();
            return AjaxResult.me().setResultObject(menus);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("操作失败："+e.getMessage());
        }
    }

    /**
     * 根据id删除菜单信息
     * @param id 菜单id
     * @return AjaxResult对象
     */
    @ApiOperation("删除菜单信息")
    @ApiImplicitParam(name = "id", value = "菜单ID", required = true, dataType = "Long",paramType = "path")
    @DeleteMapping("/{id}")
    @JiaXinPermission(name = "删除菜单信息权限",description = "删除菜单信息权限")
    public AjaxResult deleteById(
            @ApiParam(name = "主键",required = true)
            @PathVariable("id") Long id){
        try {
            menuService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("操作失败："+e.getMessage());
        }
    }

    @ApiOperation("批量删除菜单信息")
    @PatchMapping
    @JiaXinPermission(name = "批量删除菜单信息权限",description = "批量删除菜单信息权限")
    public AjaxResult batchDelete(
            @ApiParam(name = "id集合",required = true)
             @RequestBody List<Long> ids){
        try {
            menuService.batchDelete(ids);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("操作失败："+e.getMessage()).setSuccess(false);
        }
    }

    /**
     * 新增或修改菜单信息
     * @param menu 菜单信息
     * @return AjaxResult对象
     */
    @ApiOperation("新增或修改菜单信息")
    @PutMapping
    @JiaXinPermission(name = "新增或修改菜单信息权限",description = "新增或修改菜单信息权限")
    public AjaxResult addOrUpdate(@RequestBody Menu menu){
        try {
            if (Objects.nonNull(menu.getId())){
                //id不为空，则是修改操作
                menuService.update(menu);
            }else {
                //新增操作
                menuService.insert(menu);
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
    public AjaxResult queryDataByKeyword(@RequestBody MenuQuery query){
        try {
            PageList<Menu> pageList = menuService.pageList(query);
            return AjaxResult.me().setResultObject(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("操作失败："+e.getMessage());
        }
    }

    @ApiOperation("根据员工id获取菜单树")
//    @JiaXinPermission(name = "根据员工id获取菜单树")
    @GetMapping("/getMenuTree/{employeeId}")
    public AjaxResult getMenuTreeByEmpId(@PathVariable("employeeId")Long employeeId){
        try {
            List<Menu> menus = menuService.selectMenuTreeByEmployeeId(employeeId);
            return AjaxResult.me().setResultObject(menus);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取菜单树失败："+e.getMessage());
        }
    }

}
