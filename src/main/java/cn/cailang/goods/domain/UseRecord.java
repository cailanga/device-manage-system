package cn.cailang.goods.domain;

import cn.cailang.base.domain.BaseDomain;
import cn.cailang.device.domain.Devices;
import cn.cailang.org.domain.Department;
import cn.cailang.org.domain.Employee;
import io.swagger.models.auth.In;
import lombok.Data;

/**
 * @ClassName: UseRecord
 * @Description: TODO
 * @Author: cailang
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
