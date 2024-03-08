package cn.cailang.notice.mapper;

import cn.cailang.notice.domain.Notice;
import cn.cailang.base.mapper.BaseMapper;
import cn.cailang.notice.dto.NoticeRoleDTO;
import cn.cailang.notice.query.NoticeQuery;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cailang
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
