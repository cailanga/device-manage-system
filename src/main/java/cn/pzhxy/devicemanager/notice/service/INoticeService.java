package cn.pzhxy.devicemanager.notice.service;

import cn.pzhxy.devicemanager.base.utils.PageList;
import cn.pzhxy.devicemanager.notice.domain.Notice;
import cn.pzhxy.devicemanager.base.service.IBaseService;
import cn.pzhxy.devicemanager.notice.dto.NoticeHandleDTO;
import cn.pzhxy.devicemanager.notice.dto.NoticeRoleDTO;
import cn.pzhxy.devicemanager.notice.query.NoticeQuery;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lvjiaxin
 * @since 2024-03-08
 */
public interface INoticeService extends IBaseService<Notice> {
    void handle(NoticeHandleDTO dto, HttpServletRequest request);

    PageList<Notice> pageListForChecking(NoticeQuery query);

    void deleteById(Serializable id, HttpServletRequest request);

    List<Long> noticeRolesByNoticeId(Long id);

    void setNoticeRole(NoticeRoleDTO dto, HttpServletRequest request);

    PageList getNoticeByPerson(NoticeQuery query, HttpServletRequest request);
}
