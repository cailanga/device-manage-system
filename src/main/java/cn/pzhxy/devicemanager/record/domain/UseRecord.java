package cn.pzhxy.devicemanager.record.domain;

import cn.pzhxy.devicemanager.base.domain.BaseDomain;
import cn.pzhxy.devicemanager.device.domain.Devices;
import cn.pzhxy.devicemanager.goods.domain.Goods;
import cn.pzhxy.devicemanager.org.domain.Employee;
import lombok.Data;

/**
 * @ClassName: UseRecord
 * @Description: TODO
 * @Author: lvjiaxin
 * @Date: 2024/2/9 9:00
 * @Version 1.0
 **/
@Data
public class UseRecord extends BaseDomain {
    private static final long serialVersionUID = 1L;
    private Long useId;
    private Employee user;
    private Long goodsId;
    private Goods goods;
    private Devices devices;
    private Integer count;
    private Long deptParentId;
    private String deptName;
    private Long operatorId;
    private Employee operator;
    private String deptIdPath;
    private String remark;
    private Integer type;

}
