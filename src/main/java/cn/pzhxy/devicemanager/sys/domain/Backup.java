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
public class Backup extends BaseDomain{

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 备份文件名
     */
    private String backupFileName;
    /**
     * 备份时间
     */
    private Date backupTime;
    /**
     * 备份人
     */
    private Long operatorId;

    private Employee operator;
    /**
     * 描述
     */
    private String description;
}
