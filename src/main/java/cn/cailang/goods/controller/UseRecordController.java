package cn.cailang.goods.controller;

import cn.cailang.base.utils.AjaxResult;
import cn.cailang.base.utils.PageList;
import cn.cailang.device.domain.Devices;
import cn.cailang.device.service.IDevicesService;
import cn.cailang.goods.domain.Goods;
import cn.cailang.goods.domain.UseRecord;
import cn.cailang.goods.dto.UseRecordPageInfoDTO;
import cn.cailang.goods.query.GoodsQuery;
import cn.cailang.goods.service.IGoodsService;
import cn.cailang.goods.service.IUseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody UseRecord product){
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
                }else {
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
	
    //获取
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
