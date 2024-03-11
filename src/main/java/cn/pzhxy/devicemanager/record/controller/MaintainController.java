package cn.pzhxy.devicemanager.record.controller;

import cn.pzhxy.devicemanager.auth.annotation.JiaXinPermission;
import cn.pzhxy.devicemanager.record.service.IMaintainService;
import cn.pzhxy.devicemanager.record.domain.Maintain;
import cn.pzhxy.devicemanager.record.query.MaintainQuery;
import cn.pzhxy.devicemanager.base.utils.AjaxResult;
import cn.pzhxy.devicemanager.base.utils.PageList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@JiaXinPermission(name = "维修记录管理权限",description = "维修记录管理权限")
@Api(value = "维修记录管理",description="维修记录相关的功能")
@RestController
@RequestMapping("/maintain")
public class MaintainController {
    @Autowired
    public IMaintainService maintainService;


    /**
     * 保存和修改公用的
     * @param maintain  传递的实体
     * @return Ajaxresult转换结果
     */
    @ApiOperation("维修记录新增或修改")
    @JiaXinPermission(name = "维修记录新增或修改权限",description = "维修记录新增或修改权限")
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody Maintain maintain){
        try {
            if( maintain.getId()!=null){
                    maintainService.update(maintain);
            }
            else{
                maintain.setCreateTime(new Date());
                    maintainService.insert(maintain);
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
            maintainService.deleteById(id);
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
                maintainService.batchDelete(ids);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("删除对象失败！"+e.getMessage());
        }
    }
	
    //获取用户
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id")Long id)
    {
        try {
            Maintain maintain = maintainService.selectById(id);
            return AjaxResult.me().setResultObject(maintain);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取一个失败！"+e.getMessage());
        }
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @GetMapping
    public AjaxResult list(){

        try {
            List< Maintain> list = maintainService.selectAll();
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
    @ApiOperation("维修记录关键字分页查询")
    @JiaXinPermission(name = "维修记录关键字分页查询权限",description = "维修记录关键字分页查询权限")
    @PostMapping
    public AjaxResult json(@RequestBody MaintainQuery query)
    {
        try {
            PageList<Maintain> pageList = maintainService.pageList(query);
            return AjaxResult.me().setResultObject(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取分页数据失败！"+e.getMessage());
        }
    }
}
