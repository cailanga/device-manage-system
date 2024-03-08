package cn.cailang.notice.controller;

import cn.cailang.notice.service.INoticeOperaterLogService;
import cn.cailang.notice.domain.NoticeOperaterLog;
import cn.cailang.notice.query.NoticeOperaterLogQuery;
import cn.cailang.base.utils.AjaxResult;
import cn.cailang.base.utils.PageList;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
