package cn.pzhxy.devicemanager.notice.domain;

import java.util.Date;
import cn.pzhxy.devicemanager.base.domain.BaseDomain;

import cn.pzhxy.devicemanager.org.domain.Employee;
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
public class Notice extends BaseDomain{

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 通知标题
     */
    private String title;
    /**
     * 通知内容
     */
    private String content;
    /**
     * 发布人id
     */
    private Long operatorId;

    private Employee operator;
    /**
     * 发布时间
     */
    private Date createTime;
    /**
     * 状态
     */
    private Integer status;
}
