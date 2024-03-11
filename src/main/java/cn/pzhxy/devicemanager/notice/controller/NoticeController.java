package cn.pzhxy.devicemanager.notice.controller;

import cn.pzhxy.devicemanager.auth.annotation.JiaXinPermission;
import cn.pzhxy.devicemanager.base.utils.LoginUtil;
import cn.pzhxy.devicemanager.notice.dto.NoticeHandleDTO;
import cn.pzhxy.devicemanager.notice.dto.NoticeRoleDTO;
import cn.pzhxy.devicemanager.notice.service.INoticeService;
import cn.pzhxy.devicemanager.notice.domain.Notice;
import cn.pzhxy.devicemanager.notice.query.NoticeQuery;
import cn.pzhxy.devicemanager.base.utils.AjaxResult;
import cn.pzhxy.devicemanager.base.utils.PageList;
import cn.pzhxy.devicemanager.org.domain.Employee;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
@JiaXinPermission(name = "通知管理权限",description = "通知管理权限")
@Api(value = "通知管理",description="通知相关的功能")
@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    public INoticeService noticeService;


    /**
     * 保存和修改公用的
     *
     * @param notice 传递的实体
     * @return Ajaxresult转换结果
     */
    @ApiOperation("通知新增或修改")
    @JiaXinPermission(name = "通知新增或修改权限",description = "通知新增或修改权限")
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody Notice notice, HttpServletRequest request) {
        try {
            if (notice.getId() != null) {
                noticeService.update(notice);
            } else {
                notice.setCreateTime(new Date());
                Employee userInfo = LoginUtil.getUserInfo(request);
                notice.setOperatorId(userInfo.getId());
                notice.setStatus(1);
                noticeService.insert(notice);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("保存对象失败！" + e.getMessage());
        }
    }

    /**
     * 删除对象信息
     *
     * @param id
     * @return
     */
    @ApiOperation("通知删除")
    @JiaXinPermission(name = "通知删除权限",description = "通知删除权限")
    @DeleteMapping(value = "/{id}")
    public AjaxResult delete(@PathVariable("id") Long id, HttpServletRequest request) {
        try {
            noticeService.deleteById(id, request);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("删除对象失败！" + e.getMessage());
        }
    }

    /**
     * 批量删除对象信息
     *
     * @param ids
     * @return
     */
    @ApiOperation("通知批量删除")
    @JiaXinPermission(name = "通知批量删除权限",description = "通知批量删除权限")
    @PatchMapping()
    public AjaxResult batchDelete(@RequestBody List<Long> ids) {
        try {
            noticeService.batchDelete(ids);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("删除对象失败！" + e.getMessage());
        }
    }

    //获取用户
    @ApiOperation("通知信息根据id获取")
//    @JiaXinPermission(name = "通知信息根据id获取权限",description = "通知信息根据id获取权限")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id") Long id) {
        try {
            Notice notice = noticeService.selectById(id);
            return AjaxResult.me().setResultObject(notice);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取一个失败！" + e.getMessage());
        }
    }


    /**
     * 查看所有的员工信息
     *
     * @return
     */
    @ApiOperation("通知所有信息获取")
//    @JiaXinPermission(name = "通知所有信息获取权限",description = "通知所有信息获取权限")
    @GetMapping
    public AjaxResult list() {

        try {
            List<Notice> list = noticeService.selectAll();
            return AjaxResult.me().setResultObject(list);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取所有失败！" + e.getMessage());
        }
    }


    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    @ApiOperation("通知关键字分页查询")
    @JiaXinPermission(name = "通知关键字分页查询权限",description = "通知关键字分页查询权限")
    @PostMapping
    public AjaxResult json(@RequestBody NoticeQuery query) {
        try {
            PageList<Notice> pageList = noticeService.pageList(query);
            return AjaxResult.me().setResultObject(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取分页数据失败！" + e.getMessage());
        }
    }
    @ApiOperation("通知待审核查询")
    @JiaXinPermission(name = "通知待审核查询权限",description = "通知待审核查询权限")
    @PostMapping("/checking")
    public AjaxResult jsonForChecking(@RequestBody NoticeQuery query) {
        try {
            PageList<Notice> pageList = noticeService.pageListForChecking(query);
            return AjaxResult.me().setResultObject(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取分页数据失败！" + e.getMessage());
        }
    }
    @ApiOperation("通知待审核处理")
    @JiaXinPermission(name = "通知待审核处理权限",description = "通知待审核处理权限")
    @PostMapping("/handle")
    public AjaxResult handle(@RequestBody NoticeHandleDTO dto, HttpServletRequest request) {
        try {
            noticeService.handle(dto, request);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("处理失败！" + e.getMessage());
        }
    }
    @ApiOperation("获取通知可见的角色信息")
    @JiaXinPermission(name = "获取通知可见的角色信息权限",description = "获取通知可见的角色信息权限")
    @GetMapping("/noticeRolesByNoticeId/{id}")
    public AjaxResult noticeRolesByNoticeId(@PathVariable("id") Long id) {
        try {
            List<Long> roleIds = noticeService.noticeRolesByNoticeId(id);
            return AjaxResult.me().setResultObject(roleIds);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取失败！" + e.getMessage());
        }
    }
    @ApiOperation("设置通知可见的角色")
    @JiaXinPermission(name = "设置通知可见的角色权限",description = "设置通知可见的角色权限")
    @PostMapping("/setNoticeRole")
    public AjaxResult setNoticeRole(@RequestBody NoticeRoleDTO dto, HttpServletRequest request) {
        try {
            noticeService.setNoticeRole(dto,request);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("设置通知角色可见失败！" + e.getMessage());
        }
    }
    @ApiOperation("获取个人可见通知")
//    @JiaXinPermission(name = "获取个人可见通知权限",description = "获取个人可见通知权限")
    @PostMapping("/noticeByPerson")
    public AjaxResult noticeByPerson(@RequestBody NoticeQuery query,HttpServletRequest request) {
        try {
            PageList notices = noticeService.getNoticeByPerson(query,request);
            return AjaxResult.me().setResultObject(notices);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取通知失败！" + e.getMessage());
        }
    }
}
