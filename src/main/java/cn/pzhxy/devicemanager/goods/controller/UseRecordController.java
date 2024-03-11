package cn.pzhxy.devicemanager.goods.controller;

import cn.pzhxy.devicemanager.auth.annotation.JiaXinPermission;
import cn.pzhxy.devicemanager.base.utils.AjaxResult;
import cn.pzhxy.devicemanager.base.utils.PageList;
import cn.pzhxy.devicemanager.device.domain.Devices;
import cn.pzhxy.devicemanager.device.service.IDevicesService;
import cn.pzhxy.devicemanager.goods.domain.Goods;
import cn.pzhxy.devicemanager.goods.domain.UseRecord;
import cn.pzhxy.devicemanager.goods.dto.UseRecordPageInfoDTO;
import cn.pzhxy.devicemanager.goods.query.GoodsQuery;
import cn.pzhxy.devicemanager.goods.service.IGoodsService;
import cn.pzhxy.devicemanager.goods.service.IUseRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@JiaXinPermission(name = "物资设备领用管理权限",description = "物资设备领用管理权限")
@Api(value = "物资设备领用管理",description="物资设备领用相关的功能")
@RestController
@RequestMapping("/useRecord")
public class UseRecordController {
    @Autowired
    public IUseRecordService productService;
    @Autowired
    public IGoodsService goodsService;
    @Autowired
    public IDevicesService devicesService;


    /**
     * 保存和修改公用的
     * @param product  传递的实体
     * @return Ajaxresult转换结果
     */
    @ApiOperation("物资领用")
    @JiaXinPermission(name = "物资领用权限",description = "物资领用权限")
    @PutMapping("/goods")
    public AjaxResult addOrUpdateGoods(@RequestBody UseRecord product){
        try {
            if( product.getId()!=null){
                    productService.update(product);
            }
            else{
                Integer count = product.getCount();
                if (product.getType()==1){
                    //物资
                    Goods goods = goodsService.selectById(product.getGoodsId());
                    Integer count1 = goods.getCount();
                    Integer useCount = goods.getUseCount();
                    if (count+useCount>count1){
                        //超过库存
                        return AjaxResult.me().setSuccess(false).setMessage("领用数量库存不足！");
                    }else {
                        goods.setUseCount(count+useCount);
                        goodsService.update(goods);
                    }
                }
                productService.insert(product);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("保存对象失败！"+e.getMessage());
        }
    }

    /**
     * 保存和修改公用的
     * @param product  传递的实体
     * @return Ajaxresult转换结果
     */
    @ApiOperation("设备领用")
    @JiaXinPermission(name = "设备领用权限",description = "设备领用权限")
    @PutMapping("/device")
    public AjaxResult addOrUpdateDevice(@RequestBody UseRecord product){
        try {
            if( product.getId()!=null){
                productService.update(product);
            }
            else{
                Integer count = product.getCount();
                if (product.getType()==2) {
                    Devices goods = devicesService.selectById(product.getGoodsId());
                    Integer count1 = goods.getCount();
                    Integer useCount = goods.getUseCount();
                    if (count+useCount>count1){
                        //超过库存
                        return AjaxResult.me().setSuccess(false).setMessage("领用数量库存不足！");
                    }else {
                        goods.setUseCount(count+useCount);
                        devicesService.update(goods);
                    }
                }
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
    @ApiOperation("物资设备领用记录删除")
//    @JiaXinPermission(name = "物资设备领用记录删除权限",description = "物资设备领用记录删除权限")
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
    @ApiOperation("物资设备领用记录批量删除")
//    @JiaXinPermission(name = "物资设备领用记录批量删除权限",description = "物资设备领用记录批量删除权限")
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
    @ApiOperation("物资设备领用记录根据id查询")
//    @JiaXinPermission(name = "物资设备领用记录根据id查询权限",description = "物资设备领用记录根据id查询权限")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id")Long id)
    {
        try {
            UseRecord product = productService.selectById(id);
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
    @ApiOperation("物资设备领用记录查询所有信息")
//    @JiaXinPermission(name = "物资设备领用记录查询所有信息权限",description = "物资设备领用记录查询所有信息权限")
    @GetMapping
    public AjaxResult list(){

        try {
            List<UseRecord> list = productService.selectAll();
            return AjaxResult.me().setResultObject(list);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取所有失败！"+e.getMessage());
        }
    }

    /**
     * 查看所有的信息
     * @return
     */
    @ApiOperation("物资设备领用记录根据操作者查询")
    @JiaXinPermission(name = "物资设备领用记录查询权限",description = "物资设备领用记录查询权限")
    @PostMapping("/operatorId")
    public AjaxResult listForOperator(@RequestBody UseRecordPageInfoDTO dto){

        try {
            PageList list = productService.selectAllForOperator(dto);
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
    @ApiOperation("物资设备领用记录关键词分页查询")
    @JiaXinPermission(name = "物资设备领用记录关键词分页查询权限",description = "物资设备领用记录关键词分页查询权限")
    @PostMapping
    public AjaxResult json(@RequestBody GoodsQuery query)
    {
        try {
            PageList<UseRecord> pageList = productService.pageList(query);
            return AjaxResult.me().setResultObject(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取分页数据失败！"+e.getMessage());
        }
    }

}
