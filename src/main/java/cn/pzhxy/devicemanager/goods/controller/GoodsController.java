package cn.pzhxy.devicemanager.goods.controller;

import cn.pzhxy.devicemanager.auth.annotation.JiaXinPermission;
import cn.pzhxy.devicemanager.base.utils.AjaxResult;
import cn.pzhxy.devicemanager.base.utils.PageList;
import cn.pzhxy.devicemanager.goods.domain.Goods;
import cn.pzhxy.devicemanager.goods.dto.GoodsCanUseDeptDTO;
import cn.pzhxy.devicemanager.goods.dto.GoodsDeptDTO;
import cn.pzhxy.devicemanager.goods.query.GoodsQuery;
import cn.pzhxy.devicemanager.goods.service.IGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    /**
     * 设置物资可领用部门
     */
    @ApiOperation("设置物资可领用部门")
    @JiaXinPermission(name = "设置物资可领用部门权限",description = "设置物资可领用部门权限")
    @PostMapping("/setCanUseDept")
    public AjaxResult setGoodsUseDept(@RequestBody GoodsCanUseDeptDTO dto, HttpServletRequest request)
    {
        try {
            productService.setCanUseDept(dto,request);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("设置物资可领用部门失败！"+e.getMessage());
        }
    }

    /**
     * 一般登陆者通过部门来获取对应物资信息
     */
    @ApiOperation("通过部门获取物资信息")
    @JiaXinPermission(name = "通过部门获取物资信息权限",description = "通过部门获取物资信息权限")
    @PostMapping("/byDept")
    public AjaxResult jsonByDept(@RequestBody GoodsQuery query, HttpServletRequest request)
    {
        try {
            PageList<Goods> pageList = productService.pageListByDept(query,request);
            return AjaxResult.me().setResultObject(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取数据失败！"+e.getMessage());
        }
    }

    /**
     * 一般登陆者通过部门来获取对应物资信息
     */
    @ApiOperation("通过物资获取部门信息")
//    @JiaXinPermission(name = "通过物资获取部门信息权限",description = "通过物资获取部门信息权限")
    @GetMapping("/byGoods/{goodsId}")
    public AjaxResult jsonByDept(@PathVariable("goodsId")Long goodsId)
    {
        try {
            List<GoodsDeptDTO> goodsDeptDTOS = productService.selectDeptIdsByGoodsId(goodsId);
            return AjaxResult.me().setResultObject(goodsDeptDTOS);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取数据失败！"+e.getMessage());
        }
    }
}
