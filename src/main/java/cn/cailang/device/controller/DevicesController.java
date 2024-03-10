package cn.cailang.device.controller;

import cn.cailang.auth.annotation.JiaXinPermission;
import cn.cailang.base.utils.AjaxResult;
import cn.cailang.base.utils.PageList;
import cn.cailang.device.domain.Devices;
import cn.cailang.device.query.DevicesQuery;
import cn.cailang.device.service.IDevicesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/device")
@JiaXinPermission(name = "设备管理",description = "设备管理权限")
@Api(value = "设备管理",description="设备相关的CRUD功能")
public class DevicesController {
    @Autowired
    public IDevicesService productService;


    /**
     * 保存和修改公用的
     * @param product  传递的实体
     * @return Ajaxresult转换结果
     */
    @ApiOperation("设备新增或修改")
//    @JiaXinPermission(name = "设备新增或修改权限",description = "设备新增或修改权限")
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody Devices product){
        try {
            if( product.getId()!=null){
                    productService.update(product);
            }
            else{
                product.setCreateTime(new Date());
                    productService.insert(product);
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
    @ApiOperation("设备删除")
//    @JiaXinPermission(name = "设备删除权限",description = "设备删除权限")
    @DeleteMapping(value="/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            productService.deleteById(id);
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
    @ApiOperation("设备批量删除")
//    @JiaXinPermission(name = "设备批量删除权限",description = "设备批量删除权限")
    @PatchMapping()
    public AjaxResult batchDelete(@RequestBody List<Long> ids){
        try {
                productService.batchDelete(ids);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("删除对象失败！"+e.getMessage());
        }
    }
	
    //获取
    @ApiOperation("根据设备id获取设备信息")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id")Long id)
    {
        try {
            Devices product = productService.selectById(id);
            return AjaxResult.me().setResultObject(product);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取一个失败！"+e.getMessage());
        }
    }

    @ApiOperation("根据设备类型获取设备信息")
    @JiaXinPermission(name = "根据设备类型获取设备信息权限",description = "根据设备类型获取设备信息权限")
    @GetMapping("/devicesByTypeId/{id}")
    public AjaxResult getByTypeId(@PathVariable("id")Long id)
    {
        try {
            List<Devices> list = productService.selectByTypeId(id);
            return AjaxResult.me().setResultObject(list);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取失败！"+e.getMessage());
        }
    }


    /**
    * 查看所有的信息
    * @return
    */
    @ApiOperation("获取所有设备信息")
    @GetMapping
    public AjaxResult list(){

        try {
            List<Devices> list = productService.selectAll();
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
    @ApiOperation("根据关键字分页查询设备信息")
    @JiaXinPermission(name = "根据关键字分页查询设备信息权限",description = "根据关键字分页查询设备信息权限")
    @PostMapping
    public AjaxResult json(@RequestBody DevicesQuery query)
    {
        try {
            PageList<Devices> pageList = productService.pageList(query);
            return AjaxResult.me().setResultObject(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取分页数据失败！"+e.getMessage());
        }
    }
}
