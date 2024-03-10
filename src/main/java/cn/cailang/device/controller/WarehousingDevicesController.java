package cn.cailang.device.controller;

import cn.cailang.auth.annotation.JiaXinPermission;
import cn.cailang.base.utils.AjaxResult;
import cn.cailang.base.utils.PageList;
import cn.cailang.device.domain.WarehousingDevices;
import cn.cailang.device.dto.DevicesHandleDTO;
import cn.cailang.device.query.WarehousingDevicesQuery;
import cn.cailang.device.service.IWarehousingDevicesService;
import cn.cailang.goods.query.WarehousingGoodsQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@JiaXinPermission(name = "采购设备管理权限",description = "采购设备管理权限")
@Api(value = "采购设备管理",description="采购设备相关的功能")
@RestController
@RequestMapping("/nowarehousingdevices")
public class WarehousingDevicesController {
    @Autowired
    public IWarehousingDevicesService productService;


    /**
     * 保存和修改公用的
     * @param product  传递的实体
     * @return Ajaxresult转换结果
     */
    @ApiOperation("采购设备新增或修改")
    @JiaXinPermission(name = "采购设备新增或修改权限",description = "采购设备新增或修改权限")
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody WarehousingDevices product){
        try {
            if( product.getId()!=null){
                    productService.update(product);
            }
            else{
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
    @ApiOperation("采购设备删除")
    @JiaXinPermission(name = "设备删除权限",description = "设备删除权限")
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
    @ApiOperation("采购设备批量删除")
    @JiaXinPermission(name = "设备批量删除权限",description = "设备批量删除权限")
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

    /**
     * 批量入库对象信息
     * @param ids
     * @return
     */
    @ApiOperation("设备入库")
    @JiaXinPermission(name = "设备入库权限",description = "设备入库权限")
    @PatchMapping("/warehousing")
    public AjaxResult batchWarehousing(@RequestBody List<Long> ids, HttpServletRequest request){
        try {
            productService.batchWarehousing(ids,request);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("入库失败！"+e.getMessage());
        }
    }
    @ApiOperation("采购设备信息根据id查询")
    @JiaXinPermission(name = "设备日志关键字分页查询权限",description = "设备日志关键字分页查询权限")
    //获取
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id")Long id)
    {
        try {
            WarehousingDevices product = productService.selectById(id);
            return AjaxResult.me().setResultObject(product);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取一个失败！"+e.getMessage());
        }
    }


    /**
    * 查看所有的信息
    * @return
    */
    @ApiOperation("采购设备所有信息查询")
//    @JiaXinPermission(name = "采购设备所有信息查询权限",description = "采购设备所有信息查询权限")
    @GetMapping
    public AjaxResult list(){

        try {
            List<WarehousingDevices> list = productService.selectAll();
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
    @ApiOperation("采购设备关键字分页查询")
    @JiaXinPermission(name = "采购设备关键字分页查询权限",description = "采购设备关键字分页查询权限")
    @PostMapping
    public AjaxResult json(@RequestBody WarehousingGoodsQuery query)
    {
        try {
            PageList<WarehousingDevices> pageList = productService.pageList(query);
            return AjaxResult.me().setResultObject(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取分页数据失败！"+e.getMessage());
        }
    }

    @ApiOperation("设备入库待审核信息查询")
    @JiaXinPermission(name = "设备入库待审核信息查询权限",description = "设备入库待审核信息查询权限")
    @PostMapping("/checking")
    public AjaxResult jsonForChecking(@RequestBody WarehousingDevicesQuery query)
    {
        try {
            PageList<WarehousingDevices> pageList = productService.pageListForChecking(query);
            return AjaxResult.me().setResultObject(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取分页数据失败！"+e.getMessage());
        }
    }

    @ApiOperation("待审核设备处理")
    @JiaXinPermission(name = "待审核设备处理权限",description = "待审核设备处理权限")
    @PostMapping("/handle")
    public AjaxResult handle(@RequestBody DevicesHandleDTO dto, HttpServletRequest request)
    {
        try {
            productService.handle(dto,request);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("处理失败！"+e.getMessage());
        }
    }
}
