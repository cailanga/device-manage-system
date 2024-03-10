package cn.cailang.seller.controller;

import cn.cailang.auth.annotation.JiaXinPermission;
import cn.cailang.base.utils.AjaxResult;
import cn.cailang.base.utils.PageList;
import cn.cailang.seller.domain.Seller;
import cn.cailang.seller.query.SellerQuery;
import cn.cailang.seller.service.ISellerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@JiaXinPermission(name = "商家管理权限",description = "商家管理权限")
@Api(value = "商家管理",description="商家相关的CRUD功能")
@RestController
@RequestMapping("/seller")
public class SellersController {
    @Autowired
    public ISellerService productService;


    /**
     * 保存和修改公用的
     * @param product  传递的实体
     * @return Ajaxresult转换结果
     */
    @ApiOperation("商家新增或修改")
    @JiaXinPermission(name = "商家新增或修改权限",description = "商家新增或修改权限")
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody Seller product){
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
    @ApiOperation("商家删除")
    @JiaXinPermission(name = "商家删除权限",description = "商家删除权限")
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
    @ApiOperation("商家批量删除")
    @JiaXinPermission(name = "商家批量删除权限",description = "商家批量删除权限")
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
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id")Long id)
    {
        try {
            Seller product = productService.selectById(id);
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
    @GetMapping
    public AjaxResult list(){

        try {
            List<Seller> list = productService.selectAll();
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
    @ApiOperation("商家关键词分页查询")
    @JiaXinPermission(name = "商家关键词分页查询权限",description = "商家关键词分页查询权限")
    @PostMapping
    public AjaxResult json(@RequestBody SellerQuery query)
    {
        try {
            PageList<Seller> pageList = productService.pageList(query);
            return AjaxResult.me().setResultObject(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取分页数据失败！"+e.getMessage());
        }
    }
}
