package cn.pzhxy.devicemanager.sys.domain;

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
 * @since 2024-02-27
 */
@Data
public class BackupOperatorLog extends BaseDomain{

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 描述
     */
    private String description;
    /**
     * 操作时间
     */
    private Date time;
    /**
     * 操作人
     */
    private Long operatorId;

    private Employee operator;
    /**
     * 操作名称
     */
    private String operatorName;
}
