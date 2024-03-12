package cn.pzhxy.devicemanager.goods.dto;

import lombok.Data;

/**
 * @ClassName: DeviceDeptDTO
 * @Description: TODO
 * @Author: cailang
 * @Date: 2024/3/12 16:27
 * @Version 1.0
 **/
@Data
public class GoodsDeptDTO {
    private Long goodsId;
    private Long deptId;
    private String deptPath;
}
