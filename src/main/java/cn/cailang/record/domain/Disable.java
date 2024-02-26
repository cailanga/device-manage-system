package cn.cailang.record.domain;

import java.util.Date;
import cn.cailang.base.domain.BaseDomain;

import cn.cailang.org.domain.Employee;
import lombok.Data;


/**
 * <p>
 * 维修记录表
 * </p>
 *
 * @author cailang
 * @since 2024-02-26
 */
@Data
public class Disable extends BaseDomain{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 维修设备id
     */
    private String goodsId;
    /**
     * 维修原因
     */
    private String cause;
    /**
     * 描述
     */
    private String description;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 报废时间
     */
    private Date date;
    /**
     * 操作者id
     */
    private String operatorId;

    private Employee operator;
}
