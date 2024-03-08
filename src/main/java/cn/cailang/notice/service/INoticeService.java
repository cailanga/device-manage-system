package cn.cailang.notice.service;

import cn.cailang.base.utils.PageList;
import cn.cailang.notice.domain.Notice;
import cn.cailang.base.service.IBaseService;
import cn.cailang.notice.dto.NoticeHandleDTO;
import cn.cailang.notice.dto.NoticeRoleDTO;
import cn.cailang.notice.query.NoticeQuery;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cailang
 * @since 2024-03-08
 */
public interface INoticeService extends IBaseService<Notice> {
    void handle(NoticeHandleDTO dto, HttpServletRequest request);

    PageList<Notice> pageListForChecking(NoticeQuery query);

    void deleteById(Serializable id, HttpServletRequest request);

    List<Long> noticeRolesByNoticeId(Long id);

    void setNoticeRole(NoticeRoleDTO dto,HttpServletRequest request);

    PageList getNoticeByPerson(NoticeQuery query, HttpServletRequest request);
}
