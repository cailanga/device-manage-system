package cn.cailang.notice.service.impl;

import cn.cailang.base.utils.LoginUtil;
import cn.cailang.base.utils.PageList;
import cn.cailang.notice.domain.Notice;
import cn.cailang.notice.domain.NoticeOperaterLog;
import cn.cailang.notice.dto.NoticeHandleDTO;
import cn.cailang.notice.dto.NoticeRoleDTO;
import cn.cailang.notice.mapper.NoticeMapper;
import cn.cailang.notice.mapper.NoticeOperaterLogMapper;
import cn.cailang.notice.query.NoticeQuery;
import cn.cailang.notice.service.INoticeService;
import cn.cailang.base.service.impl.BaseServiceImpl;
import cn.cailang.org.domain.Employee;
import cn.cailang.org.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cailang
 * @since 2024-03-08
 */
@Service
public class NoticeServiceImpl extends BaseServiceImpl<Notice> implements INoticeService {

    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private NoticeOperaterLogMapper noticeOperaterLogMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public void handle(NoticeHandleDTO dto, HttpServletRequest request) {
        Long goodsId = dto.getNoticeId();
        String description = dto.getDescription();
        Employee userInfo = LoginUtil.getUserInfo(request);
        NoticeOperaterLog operaterLog = new NoticeOperaterLog();
        operaterLog.setType("审批");
        operaterLog.setOperatorId(userInfo.getId());

        operaterLog.setOperatorName(userInfo.getUsername());
        operaterLog.setNoticeId(dto.getNoticeId());
        Notice notice = noticeMapper.selectById(goodsId);
        operaterLog.setNoticeTitle(notice.getTitle());
        operaterLog.setUpdateTime(new Date());
        if (dto.getOperateTypeId()==1){
            //审批通过
            notice.setStatus(3);
            operaterLog.setDescription("审批通过："+description);
        }else if (dto.getOperateTypeId()==2){
            //驳回
            notice.setStatus(2);
            operaterLog.setDescription("审批驳回："+description);
        }
        noticeMapper.update(notice);
        noticeOperaterLogMapper.insert(operaterLog);
    }

    @Override
    public PageList<Notice> pageListForChecking(NoticeQuery query) {
        Long total = noticeMapper.queryTotalForChecking(query);
        if(total>0){
            List<Notice> rows = noticeMapper.queryPageListForChecking(query);
            return new PageList<>(total,rows);
        }
        return new PageList<>();
    }

    @Override
    public void deleteById(Serializable id,HttpServletRequest request) {
        super.deleteById(id);
        Employee userInfo = LoginUtil.getUserInfo(request);
        NoticeOperaterLog operaterLog = new NoticeOperaterLog();
        operaterLog.setType("删除");
        operaterLog.setOperatorId(userInfo.getId());

        operaterLog.setOperatorName(userInfo.getUsername());
        operaterLog.setNoticeId((Long) id);
        Notice notice = noticeMapper.selectById(id);
        operaterLog.setDescription("通知删除："+notice.toString());
        operaterLog.setNoticeTitle(notice.getTitle());
        operaterLog.setUpdateTime(new Date());
        noticeOperaterLogMapper.insert(operaterLog);
    }

    @Override
    public List<Long> noticeRolesByNoticeId(Long id) {
        return noticeMapper.getNoticeRolesByNoticeId(id);
    }

    @Override
    public void setNoticeRole(NoticeRoleDTO dto,HttpServletRequest request) {
        noticeMapper.deleteNoticeRoleByNoticeId(dto.getNoticeId());
        noticeMapper.setNoticeRole(dto);
        //记录日志
        Employee userInfo = LoginUtil.getUserInfo(request);
        NoticeOperaterLog operaterLog = new NoticeOperaterLog();
        operaterLog.setType("设置通知可见");
        operaterLog.setOperatorId(userInfo.getId());

        operaterLog.setOperatorName(userInfo.getUsername());
        operaterLog.setNoticeId(dto.getNoticeId());
        Notice notice = noticeMapper.selectById(dto.getNoticeId());
        operaterLog.setDescription("通知可见："+notice.toString()+" 可见角色id:"+dto.getRoleIds().toString());
        operaterLog.setNoticeTitle(notice.getTitle());
        operaterLog.setUpdateTime(new Date());
        noticeOperaterLogMapper.insert(operaterLog);
    }

    @Override
    public PageList getNoticeByPerson(NoticeQuery query, HttpServletRequest request) {
        Employee userInfo = LoginUtil.getUserInfo(request);

        List<Long> roles = employeeMapper.getEmpRolesByEmployeeId(userInfo.getId());
        roles.add(0L);
        Long total = noticeMapper.selectNoticeCountByRoleId(roles);
        if (total == 0){
            return new PageList<>();
        }
        //查询符合角色的通知信息
        List<Notice> notices = noticeMapper.selectNoticeByRoleId(roles,query);
        notices.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Notice::getId))),
                ArrayList::new
        ));
        return new PageList<>(total,notices);
    }
}
