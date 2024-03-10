package cn.cailang.sys.controller;

import cn.cailang.auth.annotation.JiaXinPermission;
import cn.cailang.sys.service.IBackupOperatorLogService;
import cn.cailang.sys.domain.BackupOperatorLog;
import cn.cailang.sys.query.BackupOperatorLogQuery;
import cn.cailang.base.utils.AjaxResult;
import cn.cailang.base.utils.PageList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@JiaXinPermission(name = "备份操作日志管理权限",description = "备份操作日志管理权限")
@Api(value = "备份操作日志管理",description="备份操作日志相关的功能")
@RestController
@RequestMapping("/backupOperaterLog")
public class BackupOperatorLogController {
    @Autowired
    public IBackupOperatorLogService backupOperatorLogService;


    /**
     * 保存和修改公用的
     * @param backupOperatorLog  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody BackupOperatorLog backupOperatorLog){
        try {
            if( backupOperatorLog.getId()!=null){
                    backupOperatorLogService.update(backupOperatorLog);
            }
            else{
                    backupOperatorLogService.insert(backupOperatorLog);
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
            backupOperatorLogService.deleteById(id);
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
                backupOperatorLogService.batchDelete(ids);
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
            BackupOperatorLog backupOperatorLog = backupOperatorLogService.selectById(id);
            return AjaxResult.me().setResultObject(backupOperatorLog);
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
            List< BackupOperatorLog> list = backupOperatorLogService.selectAll();
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
    @ApiOperation("备份操作日志关键字分页查询")
    @JiaXinPermission(name = "备份操作日志关键字分页查询权限",description = "备份操作日志关键字分页查询权限")
    @PostMapping
    public AjaxResult json(@RequestBody BackupOperatorLogQuery query)
    {
        try {
            PageList<BackupOperatorLog> pageList = backupOperatorLogService.pageList(query);
            return AjaxResult.me().setResultObject(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取分页数据失败！"+e.getMessage());
        }
    }
}
