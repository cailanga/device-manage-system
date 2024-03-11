package cn.pzhxy.devicemanager.goods.controller;

import cn.pzhxy.devicemanager.auth.annotation.JiaXinPermission;
import cn.pzhxy.devicemanager.base.utils.AjaxResult;
import cn.pzhxy.devicemanager.base.utils.PageList;
import cn.pzhxy.devicemanager.goods.domain.GoodsOperaterLog;
import cn.pzhxy.devicemanager.goods.query.GoodsOperaterLogQuery;
import cn.pzhxy.devicemanager.goods.service.IGoodsOperaterLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@JiaXinPermission(name = "物资操作日志管理权限",description = "物资操作日志管理权限")
@Api(value = "物资操作日志管理",description="物资操作日志相关的CRUD功能")
@RestController
@RequestMapping("/goodsOperaterLog")
public class GoodsOperaterLogController {
    @Autowired
    public IGoodsOperaterLogService operaterLogService;


    /**
     * 保存和修改公用的
     * @param config  传递的实体
     * @return Ajaxresult转换结果
     */
    @ApiOperation("物资操作日志新增或修改")
//    @JiaXinPermission(name = "物资操作日志新增或修改权限",description = "物资操作日志新增或修改权限")
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody GoodsOperaterLog config){
        try {
            if( config.getId()!=null){
                    operaterLogService.update(config);
            }
            else{
                    operaterLogService.insert(config);
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
    @ApiOperation("物资操作日志删除")
//    @JiaXinPermission(name = "物资操作日志删除权限",description = "物资操作日志删除权限")
    @DeleteMapping(value="/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            operaterLogService.deleteById(id);
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
    @ApiOperation("物资操作日志批量删除")
//    @JiaXinPermission(name = "物资操作日志批量删除权限",description = "物资操作日志批量删除权限")
    @PatchMapping()
    public AjaxResult batchDelete(@RequestBody List<Long> ids){
        try {
                operaterLogService.batchDelete(ids);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("删除对象失败！"+e.getMessage());
        }
    }
	
    //获取用户
    @ApiOperation("物资操作日志根据id获取")
//    @JiaXinPermission(name = "物资操作日志根据id获取权限",description = "物资操作日志根据id获取权限")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id")Long id)
    {
        try {
            GoodsOperaterLog config = operaterLogService.selectById(id);
            return AjaxResult.me().setResultObject(config);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取一个失败！"+e.getMessage());
        }
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @ApiOperation("物资操作日志所有信息获取")
//    @JiaXinPermission(name = "物资操作日志所有信息获取权限",description = "物资操作日志所有信息获取权限")
    @GetMapping
    public AjaxResult list(){

        try {
            List< GoodsOperaterLog> list = operaterLogService.selectAll();
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
    @ApiOperation("物资操作日志关键字分页查询")
    @JiaXinPermission(name = "物资操作日志关键字分页查询权限",description = "物资操作日志关键字分页查询权限")
    @PostMapping
    public AjaxResult json(@RequestBody GoodsOperaterLogQuery query)
    {
        try {
            PageList<GoodsOperaterLog> pageList = operaterLogService.pageList(query);
            return AjaxResult.me().setResultObject(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取分页数据失败！"+e.getMessage());
        }
    }
}
