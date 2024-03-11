package cn.pzhxy.devicemanager.notice.domain;

import java.util.Date;
import cn.pzhxy.devicemanager.base.domain.BaseDomain;

import lombok.Data;


/**
 * <p>
 * 
 * </p>
 *
 * @author lvjiaxin
 * @since 2024-03-08
 */
@Data
public class NoticeOperaterLog extends BaseDomain{

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 操作类型：删除，审核...
     */
    private String type;
    /**
     * 描述
     */
    private String description;
    /**
     * 操作人id
     */
    private Long operatorId;
    /**
     * 操作人名字
     */
    private String operatorName;
    /**
     * 操作时间
     */
    private Date updateTime;
    /**
     * 审批数据来源提交人
     */
    private Long originId;
    private String originName;
    /**
     * 处理的通知的id
     */
    private Long noticeId;
    private String noticeTitle;
}
