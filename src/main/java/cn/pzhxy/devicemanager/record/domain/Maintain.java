package cn.pzhxy.devicemanager.record.domain;

import java.util.Date;
import cn.pzhxy.devicemanager.base.domain.BaseDomain;

import cn.pzhxy.devicemanager.org.domain.Employee;
import lombok.Data;


/**
 * <p>
 * 维修记录表
 * </p>
 *
 * @author lvjiaxin
 * @since 2024-02-26
 */
@Data
public class Maintain extends BaseDomain{

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
     * 维修时间
     */
    private Date date;
    /**
     * 操作者id
     */
    private String operatorId;

    private Employee operator;
}
