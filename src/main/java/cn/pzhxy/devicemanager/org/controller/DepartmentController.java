package cn.pzhxy.devicemanager.org.controller;

import cn.pzhxy.devicemanager.auth.annotation.JiaXinPermission;
import cn.pzhxy.devicemanager.base.utils.AjaxResult;
import cn.pzhxy.devicemanager.base.utils.PageList;
import cn.pzhxy.devicemanager.org.domain.Department;
import cn.pzhxy.devicemanager.org.query.DepartmentQuery;
import cn.pzhxy.devicemanager.org.service.IDepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName: DepartmentController
 * @Description: 部门控制层
 * @Author: 2026972757@qq.com
 * @Date: 2024/2/23 16:13
 * @Version 1.0
 **/
@Api(value = "部门管理",description="部门相关的CRUD功能")
@RestController
@RequestMapping("/department")
@JiaXinPermission(name = "部门管理",description = "部门管理权限")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;

    /**
     * 通过id查询部门信息
     * @param id 部门id
     * @return AjaxResult对象
     */
//    @JiaXinPermission(name = "通过id查询部门信息",description = "通过id查询部门信息")
    @ApiOperation("通过id查询部门信息")
    @ApiImplicitParam(name = "id", value = "部门ID", required = true, dataType = "Long",paramType = "path")
    @GetMapping("/{id}")
    public AjaxResult selectById(
            @ApiParam(name = "主键",required = true)
            @PathVariable("id") Long id) {
        try {
            Department department = departmentService.selectById(id);
            return AjaxResult.me().setResultObject(department);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("操作失败："+e.getMessage());
        }
    }

    /**
     * 查询所有部门信息
     * @return AjaxResult对象
     */
//    @JiaXinPermission(name = "查询所有部门信息",description = "查询所有部门信息")
    @ApiOperation("查询所有部门信息")
    @GetMapping
    public AjaxResult selectAll() {
        try {
            List<Department> departments = departmentService.selectAll();
            return AjaxResult.me().setResultObject(departments);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("操作失败："+e.getMessage());
        }
    }

    /**
     * 根据id删除部门信息
     * @param id 部门id
     * @return AjaxResult对象
     */
    @JiaXinPermission(name = "删除部门信息权限",description = "删除部门信息权限")
    @ApiOperation("根据id删除部门信息")
    @ApiImplicitParam(name = "id", value = "部门ID", required = true, dataType = "Long",paramType = "path")
    @DeleteMapping("/{id}")
    public AjaxResult deleteById(
            @ApiParam(name = "主键",required = true)
            @PathVariable("id") Long id){
        try {
            departmentService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("操作失败："+e.getMessage());
        }
    }

    /**
     * 批量删除
     * @param ids id集合
     * @return AjaxResult对象
     */
    @JiaXinPermission(name = "批量删除权限",description = "批量删除权限")
    @ApiOperation("根据ids批量删除部门信息")
    @PatchMapping
    public AjaxResult batchDelete(
            @ApiParam(name = "id集合",required = true)
             @RequestBody List<Long> ids){
        try {
            departmentService.batchDelete(ids);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("操作失败："+e.getMessage()).setSuccess(false);
        }
    }

    /**
     * 新增或修改部门信息
     * @param department 部门信息
     * @return AjaxResult对象
     */
    @JiaXinPermission(name = "新增或修改部门信息权限",description = "新增或修改部门信息权限")
    @ApiOperation("新增或修改部门信息")
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody Department department){
        try {
            if (Objects.nonNull(department.getId())){
                //id不为空，则是修改操作
                departmentService.update(department);
            }else {
                //新增操作
                departmentService.insert(department);
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
    @JiaXinPermission(name = "根据关键字进行分页查询权限",description = "根据关键字进行分页查询权限")
    @ApiOperation("根据关键字进行分页查询")
    @PostMapping
    public AjaxResult queryDataByKeyword(@RequestBody DepartmentQuery query){
        try {
            PageList<Department> pageList = departmentService.pageList(query);
            return AjaxResult.me().setResultObject(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("操作失败："+e.getMessage());
        }
    }

    /**
     * 查询部门树
     * @return 一级部门及其子部门数据
     */
//    @JiaXinPermission(name = "查询部门树",description = "查询部门树")
    @ApiOperation("查询所有一级部门信息及其子部门信息")
    @GetMapping("/tree")
    public AjaxResult queryFirstDeptAndChildren(){
        try {
            List<Department> departments = departmentService.queryFirstDeptAndChildren();
            return AjaxResult.me().setResultObject(departments);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("操作失败："+e.getMessage());
        }
    }
}
