package cn.pzhxy.devicemanager.auth.controller;

import cn.pzhxy.devicemanager.auth.annotation.JiaXinPermission;
import cn.pzhxy.devicemanager.auth.domain.Permission;
import cn.pzhxy.devicemanager.auth.query.PermissionQuery;
import cn.pzhxy.devicemanager.auth.service.IPermissionService;
import cn.pzhxy.devicemanager.base.utils.AjaxResult;
import cn.pzhxy.devicemanager.base.utils.PageList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: PermissionController
 * @Description: 权限管理
 * @Author: 2026972757@qq.com
 * @Date: 2024/2/26 10:34
 * @Version 1.0
 **/
@Api(description = "权限管理")
@RestController
@RequestMapping("/permission")
@JiaXinPermission(name = "权限管理权限",description = "权限管理权限")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    /**
     * 根据关键字进行分页查询
     * @param query 查询对象
     * @return AjaxResult
     */
    @ApiOperation("根据关键字进行分页查询")
    @JiaXinPermission(name = "根据关键字进行分页查询权限",description = "根据关键字进行分页查询权限")
    @PostMapping("/pageList")
    public AjaxResult pageList(@RequestBody PermissionQuery query){
        try {
            PageList<Permission> pageList = permissionService.pageList(query);
            return AjaxResult.me().setResultObject(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("操作失败："+e.getMessage());
        }
    }

    /**
     * 通过员工id获取权限
     * @param employeeId 员工id
     * @return AjaxResult
     */
//    @JiaXinPermission(name = "通过员工id获取权限")
    @ApiOperation("通过员工id获取权限")
    @GetMapping("/getPermissionsByEmployeeId/{employeeId}")
    public AjaxResult getPermissionsByEmployeeId(@PathVariable("employeeId") Long employeeId){
        try {
            List<String> sns = permissionService.getSnsByEmployeeId(employeeId);
            return AjaxResult.me().setResultObject(sns);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取权限失败："+e.getMessage());
        }
    }
}
