package cn.cailang.statistics.vo;

import lombok.Data;

/**
 * @ClassName: DeviceTotalInfo
 * @Description: TODO
 * @Author: cailang
 * @Date: 2024/3/4 9:15
 * @Version 1.0
 **/
@Data
public class DeviceTotalInfoVO {
    //设备总数量
    private Long totalNum;
    //已入库设备数量
    private Long inNum;
    //未入库设备数量
    private Long noInNum;
    //报废设备数量
    private Long disabledNum;
    //可用设备数量
    private Long canUseNum;
}
