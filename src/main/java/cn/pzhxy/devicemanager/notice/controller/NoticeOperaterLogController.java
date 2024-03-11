package cn.pzhxy.devicemanager.notice.controller;

import cn.pzhxy.devicemanager.auth.annotation.JiaXinPermission;
import cn.pzhxy.devicemanager.notice.service.INoticeOperaterLogService;
import cn.pzhxy.devicemanager.notice.domain.NoticeOperaterLog;
import cn.pzhxy.devicemanager.notice.query.NoticeOperaterLogQuery;
import cn.pzhxy.devicemanager.base.utils.AjaxResult;
import cn.pzhxy.devicemanager.base.utils.PageList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@JiaXinPermission(name = "通知操作日志管理权限",description = "通知操作日志管理权限")
@Api(value = "通知操作日志管理",description="通知操作日志相关的功能")
@RestController
@RequestMapping("/noticeOperaterLog")
public class NoticeOperaterLogController {
    @Autowired
    public INoticeOperaterLogService noticeOperaterLogService;


    /**
     * 保存和修改公用的
     * @param noticeOperaterLog  传递的实体
     * @return Ajaxresult转换结果
     */
    @ApiOperation("通知操作日志新增或修改")
//    @JiaXinPermission(name = "通知操作日志新增或修改权限",description = "通知操作日志新增或修改权限")
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody NoticeOperaterLog noticeOperaterLog){
        try {
            if( noticeOperaterLog.getId()!=null){
                    noticeOperaterLogService.update(noticeOperaterLog);
            }
            else{
                    noticeOperaterLogService.insert(noticeOperaterLog);
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
    @ApiOperation("通知操作日志删除")
//    @JiaXinPermission(name = "通知操作日志删除权限",description = "通知操作日志删除权限")
    @DeleteMapping(value="/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            noticeOperaterLogService.deleteById(id);
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
    @ApiOperation("通知操作日志批量删除")
//    @JiaXinPermission(name = "通知操作日志批量删除权限",description = "通知操作日志批量删除权限")
    @PatchMapping()
    public AjaxResult batchDelete(@RequestBody List<Long> ids){
        try {
                noticeOperaterLogService.batchDelete(ids);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("删除对象失败！"+e.getMessage());
        }
    }
	
    //获取用户
    @ApiOperation("通知操作日志根据id获取")
//    @JiaXinPermission(name = "通知操作日志根据id获取权限",description = "通知操作日志根据id获取权限")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id")Long id)
    {
        try {
            NoticeOperaterLog noticeOperaterLog = noticeOperaterLogService.selectById(id);
            return AjaxResult.me().setResultObject(noticeOperaterLog);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取一个失败！"+e.getMessage());
        }
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @ApiOperation("通知操作日志所有信息获取")
//    @JiaXinPermission(name = "通知操作日志所有信息获取权限",description = "通知操作日志所有信息获取权限")
    @GetMapping
    public AjaxResult list(){

        try {
            List< NoticeOperaterLog> list = noticeOperaterLogService.selectAll();
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
    @ApiOperation("通知操作日志关键字分页查询")
    @JiaXinPermission(name = "通知操作日志关键字分页查询权限",description = "通知操作日志关键字分页查询权限")
    @PostMapping
    public AjaxResult json(@RequestBody NoticeOperaterLogQuery query)
    {
        try {
            PageList<NoticeOperaterLog> pageList = noticeOperaterLogService.pageList(query);
            return AjaxResult.me().setResultObject(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取分页数据失败！"+e.getMessage());
        }
    }
}
