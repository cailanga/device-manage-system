package cn.cailang.goods.controller;

import cn.cailang.auth.annotation.JiaXinPermission;
import cn.cailang.base.utils.AjaxResult;
import cn.cailang.base.utils.PageList;
import cn.cailang.goods.domain.Goods;
import cn.cailang.goods.domain.WarehousingGoods;
import cn.cailang.goods.dto.GoodsHandleDTO;
import cn.cailang.goods.query.GoodsQuery;
import cn.cailang.goods.query.WarehousingGoodsQuery;
import cn.cailang.goods.service.IWarehousingGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@JiaXinPermission(name = "采购物资管理权限",description = "采购物资管理权限")
@Api(value = "采购物资管理",description="采购物资相关的功能")
@RestController
@RequestMapping("/nowarehousinggoods")
public class WarehousingGoodsController {
    @Autowired
    public IWarehousingGoodsService productService;


    /**
     * 保存和修改公用的
     * @param product  传递的实体
     * @return Ajaxresult转换结果
     */
    @ApiOperation("采购物资新增或修改")
    @JiaXinPermission(name = "采购物资新增或修改权限",description = "采购物资新增或修改权限")
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody WarehousingGoods product){
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
    @ApiOperation("采购物资删除")
    @JiaXinPermission(name = "采购物资删除权限",description = "采购物资删除权限")
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
    @ApiOperation("采购物资批量删除")
    @JiaXinPermission(name = "采购物资批量删除权限",description = "采购物资批量删除权限")
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
    @ApiOperation("采购物资入库")
    @JiaXinPermission(name = "采购物资入库权限",description = "采购物资入库权限")
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
	
    //获取
    @ApiOperation("采购物资根据id查询")
//    @JiaXinPermission(name = "采购物资根据id查询权限",description = "采购物资根据id查询权限")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id")Long id)
    {
        try {
            WarehousingGoods product = productService.selectById(id);
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
    @ApiOperation("采购物资所有信息查询")
//    @JiaXinPermission(name = "采购物资所有信息查询权限",description = "采购物资所有信息查询权限")
    @GetMapping
    public AjaxResult list(){

        try {
            List<WarehousingGoods> list = productService.selectAll();
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
    @ApiOperation("采购物资关键字分页查询")
    @JiaXinPermission(name = "采购物资关键字分页查询权限",description = "采购物资关键字分页查询权限")
    @PostMapping
    public AjaxResult json(@RequestBody WarehousingGoodsQuery query)
    {
        try {
            PageList<WarehousingGoods> pageList = productService.pageList(query);
            return AjaxResult.me().setResultObject(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取分页数据失败！"+e.getMessage());
        }
    }
    @ApiOperation("待审核物资查询")
    @JiaXinPermission(name = "待审核物资查询权限",description = "待审核物资查询权限")
    @PostMapping("/checking")
    public AjaxResult jsonForChecking(@RequestBody WarehousingGoodsQuery query)
    {
        try {
            PageList<WarehousingGoods> pageList = productService.pageListForChecking(query);
            return AjaxResult.me().setResultObject(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取分页数据失败！"+e.getMessage());
        }
    }
    @ApiOperation("待审核物资处理")
    @JiaXinPermission(name = "待审核物资处理权限",description = "待审核物资处理权限")
    @PostMapping("/handle")
    public AjaxResult handle(@RequestBody GoodsHandleDTO dto,HttpServletRequest request)
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
