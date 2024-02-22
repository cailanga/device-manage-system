package cn.cailang.goods.controller;

import cn.cailang.base.utils.AjaxResult;
import cn.cailang.base.utils.PageList;
import cn.cailang.goods.domain.Goods;
import cn.cailang.goods.domain.WarehousingGoods;
import cn.cailang.goods.dto.GoodsHandleDTO;
import cn.cailang.goods.query.GoodsQuery;
import cn.cailang.goods.query.WarehousingGoodsQuery;
import cn.cailang.goods.service.IWarehousingGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
