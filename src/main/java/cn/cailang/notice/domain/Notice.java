package cn.cailang.notice.domain;

import java.math.BigDecimal;
import java.util.Date;
import cn.cailang.base.domain.BaseDomain;

import cn.cailang.org.domain.Employee;
import lombok.Data;


/**
 * <p>
 * 
 * </p>
 *
 * @author cailang
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
