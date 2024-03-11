package cn.pzhxy.devicemanager.notice.mapper;

import cn.pzhxy.devicemanager.notice.domain.Notice;
import cn.pzhxy.devicemanager.base.mapper.BaseMapper;
import cn.pzhxy.devicemanager.notice.dto.NoticeRoleDTO;
import cn.pzhxy.devicemanager.notice.query.NoticeQuery;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lvjiaxin
 * @since 2024-03-08
 */
public interface NoticeMapper extends BaseMapper<Notice> {
    Long queryTotalForChecking(NoticeQuery query);

    List<Notice> queryPageListForChecking(NoticeQuery query);

    List<Long> getNoticeRolesByNoticeId(Long id);

    void setNoticeRole(NoticeRoleDTO dto);

    void deleteNoticeRoleByNoticeId(Long noticeId);

    List<Notice> selectNoticeByRoleId(List<Long> roleIds,NoticeQuery query);

    Long selectNoticeCountByRoleId(List<Long> roleIds);
}
