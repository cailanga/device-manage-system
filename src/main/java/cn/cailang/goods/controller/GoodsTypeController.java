package cn.cailang.goods.controller;

import cn.cailang.auth.annotation.JiaXinPermission;
import cn.cailang.base.utils.AjaxResult;
import cn.cailang.base.utils.PageList;
import cn.cailang.goods.domain.GoodsType;
import cn.cailang.goods.query.GoodsTypeQuery;
import cn.cailang.goods.service.IGoodsTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@JiaXinPermission(name = "物资类型管理权限",description = "物资类型管理权限")
@Api(value = "物资类型管理",description="物资类型相关的CRUD功能")
@RestController
@RequestMapping("/goodsType")
public class GoodsTypeController {
    @Autowired
    public IGoodsTypeService productTypeService;


    /**
     * 保存和修改公用的
     * @param productType  传递的实体
     * @return Ajaxresult转换结果
     */
    @ApiOperation("物资类型新增或修改")
    @JiaXinPermission(name = "物资类型新增或修改权限",description = "物资类型新增或修改权限")
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody GoodsType productType){
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
    @ApiOperation("物资类型删除")
    @JiaXinPermission(name = "物资类型删除权限",description = "物资类型删除权限")
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
    @ApiOperation("物资类型批量删除")
    @JiaXinPermission(name = "物资类型批量删除权限",description = "物资类型批量删除权限")
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
    @ApiOperation("物资类型根据id获取")
//    @JiaXinPermission(name = "物资类型根据id获取权限",description = "物资类型根据id获取权限")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id")Long id)
    {
        try {
            GoodsType productType = productTypeService.selectById(id);
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
    @ApiOperation("物资类型所有信息获取")
//    @JiaXinPermission(name = "物资类型所有信息获取权限",description = "物资类型所有信息获取权限")
    @GetMapping
    public AjaxResult list(){

        try {
            List< GoodsType> list = productTypeService.selectAll();
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
    @ApiOperation("物资类型关键字分页获取")
    @JiaXinPermission(name = "物资类型关键字分页获取权限",description = "物资类型关键字分页获取权限")
    @PostMapping
    public AjaxResult json(@RequestBody GoodsTypeQuery query)
    {
        try {
            PageList<GoodsType> pageList = productTypeService.pageList(query);
            return AjaxResult.me().setResultObject(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取分页数据失败！"+e.getMessage());
        }
    }

    /**
     * 查询物资类型数据（一级物资）
     * @return PageList 物资类型数据
     */
    @ApiOperation("物资类型父级类型获取")
//    @JiaXinPermission(name = "物资类型父级类型获取权限",description = "物资类型父级类型获取权限")
    @GetMapping("/parentTypes")
    public AjaxResult parentTypes()
    {
        try {
            List<GoodsType> parentTypes = productTypeService.selectFirstTypes();
            return AjaxResult.me().setResultObject(parentTypes);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取物资类型数据失败！"+e.getMessage());
        }
    }
}
