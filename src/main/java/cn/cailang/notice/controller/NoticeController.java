package cn.cailang.notice.controller;

import cn.cailang.base.utils.LoginUtil;
import cn.cailang.device.dto.DevicesHandleDTO;
import cn.cailang.notice.dto.NoticeHandleDTO;
import cn.cailang.notice.dto.NoticeRoleDTO;
import cn.cailang.notice.service.INoticeService;
import cn.cailang.notice.domain.Notice;
import cn.cailang.notice.query.NoticeQuery;
import cn.cailang.base.utils.AjaxResult;
import cn.cailang.base.utils.PageList;
import cn.cailang.org.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

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

    @PostMapping("/setNoticeRole")
    public AjaxResult setNoticeRole(@RequestBody NoticeRoleDTO dto,HttpServletRequest request) {
        try {
            noticeService.setNoticeRole(dto,request);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("设置通知角色可见失败！" + e.getMessage());
        }
    }

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
