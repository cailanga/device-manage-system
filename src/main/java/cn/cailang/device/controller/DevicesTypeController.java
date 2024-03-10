package cn.cailang.device.controller;

import cn.cailang.auth.annotation.JiaXinPermission;
import cn.cailang.base.utils.AjaxResult;
import cn.cailang.base.utils.PageList;
import cn.cailang.device.domain.DevicesType;
import cn.cailang.device.query.DevicesTypeQuery;
import cn.cailang.device.service.IDevicesTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@JiaXinPermission(name = "设备类型管理权限",description = "设备类型管理权限")
@Api(value = "设备类型管理",description="设备类型相关的CRUD功能")
@RestController
@RequestMapping("/deviceType")
public class DevicesTypeController {
    @Autowired
    public IDevicesTypeService productTypeService;


    /**
     * 保存和修改公用的
     * @param productType  传递的实体
     * @return Ajaxresult转换结果
     */
    @ApiOperation("设备类型添加或修改")
    @JiaXinPermission(name = "设备类型添加或修改权限",description = "设备类型添加或修改权限")
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody DevicesType productType){
        try {
            if( productType.getId()!=null){
                    productTypeService.update(productType);
            }
            else{
                    productTypeService.insert(productType);
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
    @ApiOperation("设备类型删除")
    @JiaXinPermission(name = "设备类型删除权限",description = "设备类型删除权限")
    @DeleteMapping(value="/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            productTypeService.deleteById(id);
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
    @ApiOperation("设备类型批量删除")
    @JiaXinPermission(name = "设备类型批量删除权限",description = "设备类型批量删除权限")
    @PatchMapping()
    public AjaxResult batchDelete(@RequestBody List<Long> ids){
        try {
                productTypeService.batchDelete(ids);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("删除对象失败！"+e.getMessage());
        }
    }
    @ApiOperation("设备类型根据id查询")
//    @JiaXinPermission(name = "设备类型根据id查询权限",description = "设备类型根据id查询权限")
    //获取
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id")Long id)
    {
        try {
            DevicesType productType = productTypeService.selectById(id);
            return AjaxResult.me().setResultObject(productType);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取一个失败！"+e.getMessage());
        }
    }


    /**
    * 查看所有的信息
    * @return
    */
    @ApiOperation("设备类型所有信息查询")
//    @JiaXinPermission(name = "设备类型所有信息查询权限",description = "设备类型所有类型查询权限")
    @GetMapping
    public AjaxResult list(){

        try {
            List< DevicesType> list = productTypeService.selectAll();
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
    @ApiOperation("设备类型关键字分页查询")
    @JiaXinPermission(name = "设备类型关键字分页查询权限",description = "设备类型关键字分页查询权限")
    @PostMapping
    public AjaxResult json(@RequestBody DevicesTypeQuery query)
    {
        try {
            PageList<DevicesType> pageList = productTypeService.pageList(query);
            return AjaxResult.me().setResultObject(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取分页数据失败！"+e.getMessage());
        }
    }

    /**
     * 查询设备类型数据（一级设备）
     * @return PageList 设备类型数据
     */
    @ApiOperation("设备类型父级类型查询")
//    @JiaXinPermission(name = "设备类型父级类型查询权限",description = "设备类型父级类型查询权限")
    @GetMapping("/parentTypes")
    public AjaxResult parentTypes()
    {
        try {
            List<DevicesType> parentTypes = productTypeService.selectFirstTypes();
            return AjaxResult.me().setResultObject(parentTypes);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取设备类型数据失败！"+e.getMessage());
        }
    }
}
