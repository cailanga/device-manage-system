package cn.cailang.seller.controller;

import cn.cailang.auth.annotation.JiaXinPermission;
import cn.cailang.base.utils.AjaxResult;
import cn.cailang.base.utils.PageList;
import cn.cailang.seller.domain.SellerType;
import cn.cailang.seller.query.SellerTypeQuery;
import cn.cailang.seller.service.ISellerTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@JiaXinPermission(name = "商家类型管理权限",description = "商家类型管理权限")
@Api(value = "商家类型管理",description="商家类型相关的功能")
@RestController
@RequestMapping("/sellerType")
public class SellersTypeController {
    @Autowired
    public ISellerTypeService productTypeService;


    /**
     * 保存和修改公用的
     * @param productType  传递的实体
     * @return Ajaxresult转换结果
     */
    @ApiOperation("商家类型新增或修改")
    @JiaXinPermission(name = "商家类型新增或修改权限",description = "商家类型新增或修改权限")
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody SellerType productType){
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
    @ApiOperation("商家类型删除")
    @JiaXinPermission(name = "商家类型删除权限",description = "商家类型删除权限")
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
    @ApiOperation("商家类型批量删除")
    @JiaXinPermission(name = "商家类型批量删除权限",description = "商家类型批量删除权限")
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
	
    //获取
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id")Long id)
    {
        try {
            SellerType productType = productTypeService.selectById(id);
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
    @GetMapping
    public AjaxResult list(){

        try {
            List< SellerType> list = productTypeService.selectAll();
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
    @ApiOperation("商家类型关键字分页查询")
    @JiaXinPermission(name = "商家类型关键字分页查询权限",description = "商家类型关键字分页查询权限")
    @PostMapping
    public AjaxResult json(@RequestBody SellerTypeQuery query)
    {
        try {
            PageList<SellerType> pageList = productTypeService.pageList(query);
            return AjaxResult.me().setResultObject(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取分页数据失败！"+e.getMessage());
        }
    }

    /**
     * 查询产品类型数据（一级产品）
     * @return PageList 产品类型数据
     */
    @ApiOperation("商家类型父级类型查询")
//    @JiaXinPermission(name = "商家类型父级类型查询权限",description = "商家类型父级类型权限")
    @GetMapping("/parentTypes")
    public AjaxResult parentTypes()
    {
        try {
            List<SellerType> parentTypes = productTypeService.selectFirstTypes();
            return AjaxResult.me().setResultObject(parentTypes);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取产品类型数据失败！"+e.getMessage());
        }
    }
}
