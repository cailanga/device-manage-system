package cn.pzhxy.devicemanager.device.controller;

import cn.pzhxy.devicemanager.auth.annotation.JiaXinPermission;
import cn.pzhxy.devicemanager.base.utils.AjaxResult;
import cn.pzhxy.devicemanager.base.utils.PageList;
import cn.pzhxy.devicemanager.device.domain.DevicesOperaterLog;
import cn.pzhxy.devicemanager.device.service.IDevicesOperaterLogService;
import cn.pzhxy.devicemanager.goods.query.GoodsOperaterLogQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@JiaXinPermission(name = "设备信息操作日志权限",description = "设备信息操作日志权限")
@Api(value = "设备信息操作日志管理",description="设备信息操作日志相关的CRUD功能")
@RestController
@RequestMapping("/devicesOperaterLog")
public class DevicesOperaterLogController {
    @Autowired
    public IDevicesOperaterLogService operaterLogService;


    /**
     * 保存和修改公用的
     * @param config  传递的实体
     * @return Ajaxresult转换结果
     */
    @ApiOperation("设备日志新增或修改")
//    @JiaXinPermission(name = "设备日志新增或修改权限",description = "设备日志新增或修改权限")
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody DevicesOperaterLog config){
        try {
            if( config.getId()!=null){
                    operaterLogService.update(config);
            }
            else{
                    operaterLogService.insert(config);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("保存对象失败！"+e.getMessage());
        }
    }
    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @ApiOperation("设备操作日志删除")
//    @JiaXinPermission(name = "设备操作日志删除权限",description = "设备操作日志删除权限")
    @DeleteMapping(value="/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            operaterLogService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("删除对象失败！"+e.getMessage());
        }
    }

    /**
    * 批量删除对象信息
    * @param ids
    * @return
    */
    @ApiOperation("设备操作日志批量删除")
//    @JiaXinPermission(name = "设备操作日志批量删除权限",description = "设备操作日志批量删除权限")
    @PatchMapping()
    public AjaxResult batchDelete(@RequestBody List<Long> ids){
        try {
                operaterLogService.batchDelete(ids);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("删除对象失败！"+e.getMessage());
        }
    }

    @ApiOperation("根据设备操作日志id获取日志信息")
//    @JiaXinPermission(name = "根据设备操作日志id获取日志信息权限",description = "根据设备操作日志id获取日志信息权限")
    //获取用户
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id")Long id)
    {
        try {
            DevicesOperaterLog config = operaterLogService.selectById(id);
            return AjaxResult.me().setResultObject(config);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取一个失败！"+e.getMessage());
        }
    }


    /**
    * 查看所有的设备操作日志信息
    * @return
    */
    @ApiOperation("查看所有设备操作日志信息")
//    @JiaXinPermission(name = "查看所有设备操作日志权限",description = "查看所有设备操作日志信息权限")
    @GetMapping
    public AjaxResult list(){

        try {
            List<DevicesOperaterLog> list = operaterLogService.selectAll();
            return AjaxResult.me().setResultObject(list);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取所有失败！"+e.getMessage());
        }
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation("设备日志关键字分页查询")
    @JiaXinPermission(name = "设备日志关键字分页查询权限",description = "设备日志关键字分页查询权限")
    @PostMapping
    public AjaxResult json(@RequestBody GoodsOperaterLogQuery query)
    {
        try {
            PageList<DevicesOperaterLog> pageList = operaterLogService.pageList(query);
            return AjaxResult.me().setResultObject(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取分页数据失败！"+e.getMessage());
        }
    }
}
