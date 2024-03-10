package cn.cailang.goods.controller;

import cn.cailang.auth.annotation.JiaXinPermission;
import cn.cailang.base.utils.AjaxResult;
import cn.cailang.base.utils.PageList;
import cn.cailang.device.domain.Devices;
import cn.cailang.goods.domain.Goods;
import cn.cailang.goods.query.GoodsQuery;
import cn.cailang.goods.service.IGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@JiaXinPermission(name = "物资管理权限",description = "物资管理权限")
@Api(value = "物资管理",description="物资相关的CRUD功能")
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    public IGoodsService productService;


    /**
     * 保存和修改公用的
     * @param product  传递的实体
     * @return Ajaxresult转换结果
     */
    @ApiOperation("物资新增或修改")
//    @JiaXinPermission(name = "物资新增或修改权限",description = "物资新增或修改权限")
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody Goods product){
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
    @ApiOperation("物资删除")
//    @JiaXinPermission(name = "物资删除权限",description = "物资删除权限")
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
    @ApiOperation("物资批量删除")
//    @JiaXinPermission(name = "物资批量删除权限",description = "物资批量删除权限")
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
    @ApiOperation("物资信息根据id获取")
//    @JiaXinPermission(name = "物资信息根据id获取权限",description = "物资信息根据id获取权限")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id")Long id)
    {
        try {
            Goods product = productService.selectById(id);
            return AjaxResult.me().setResultObject(product);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取一个失败！"+e.getMessage());
        }
    }
    @ApiOperation("物资信息根据类型id获取")
    @JiaXinPermission(name = "物资信息根据类型获取权限",description = "物资信息根据获取权限")
    @GetMapping("/goodsByTypeId/{id}")
    public AjaxResult getByTypeId(@PathVariable("id")Long id)
    {
        try {
            List<Goods> list = productService.selectByTypeId(id);
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
    @ApiOperation("物资信息所有获取")
//    @JiaXinPermission(name = "物资信息所有获取权限",description = "物资信息所有获取权限")
    @GetMapping
    public AjaxResult list(){

        try {
            List<Goods> list = productService.selectAll();
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
    @ApiOperation("物资信息关键字分页查询")
    @JiaXinPermission(name = "物资信息关键字分页查询权限",description = "物资信息关键字分页查询权限")
    @PostMapping
    public AjaxResult json(@RequestBody GoodsQuery query)
    {
        try {
            PageList<Goods> pageList = productService.pageList(query);
            return AjaxResult.me().setResultObject(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取分页数据失败！"+e.getMessage());
        }
    }

}
