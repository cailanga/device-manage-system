package cn.pzhxy.devicemanager.record.controller;

import cn.pzhxy.devicemanager.auth.annotation.JiaXinPermission;
import cn.pzhxy.devicemanager.record.service.IDisableService;
import cn.pzhxy.devicemanager.record.domain.Disable;
import cn.pzhxy.devicemanager.record.query.DisableQuery;
import cn.pzhxy.devicemanager.base.utils.AjaxResult;
import cn.pzhxy.devicemanager.base.utils.PageList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@JiaXinPermission(name = "报废记录管理权限",description = "报废记录管理权限")
@Api(value = "报废记录管理",description="报废记录相关的功能")
@RestController
@RequestMapping("/disable")
public class DisableController {
    @Autowired
    public IDisableService disableService;


    /**
     * 保存和修改公用的
     * @param disable  传递的实体
     * @return Ajaxresult转换结果
     */
    @ApiOperation("报废记录新增或修改")
    @JiaXinPermission(name = "报废记录新增或修改权限",description = "报废记录新增或修改权限")
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody Disable disable){
        try {
            if( disable.getId()!=null){
                    disableService.update(disable);
            }
            else{
                disable.setCreateTime(new Date());
                    disableService.insert(disable);
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
    @ApiOperation("报废记录删除")
//    @JiaXinPermission(name = "报废记录删除权限",description = "报废记录删除权限")
    @DeleteMapping(value="/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            disableService.deleteById(id);
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
    @ApiOperation("报废记录批量删除")
//    @JiaXinPermission(name = "报废记录批量删除权限",description = "报废记录批量删除权限")
    @PatchMapping()
    public AjaxResult batchDelete(@RequestBody List<Long> ids){
        try {
                disableService.batchDelete(ids);
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
            Disable disable = disableService.selectById(id);
            return AjaxResult.me().setResultObject(disable);
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
            List< Disable> list = disableService.selectAll();
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
    @ApiOperation("报废记录关键字分页查询")
    @JiaXinPermission(name = "报废记录关键字分页查询权限",description = "报废记录关键字分页查询权限")
    @PostMapping
    public AjaxResult json(@RequestBody DisableQuery query)
    {
        try {
            PageList<Disable> pageList = disableService.pageList(query);
            return AjaxResult.me().setResultObject(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取分页数据失败！"+e.getMessage());
        }
    }
}
