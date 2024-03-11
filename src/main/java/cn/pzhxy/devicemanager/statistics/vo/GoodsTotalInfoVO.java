package cn.pzhxy.devicemanager.statistics.vo;

import lombok.Data;

/**
 * @ClassName: DeviceTotalInfo
 * @Description: TODO
 * @Author: lvjiaxin
 * @Date: 2024/3/4 9:15
 * @Version 1.0
 **/
@Data
public class GoodsTotalInfoVO {
    //物资总数量
    private Long totalNum;
    //已入库物资数量
    private Long inNum;
    //未入库物资数量
    private Long noInNum;
    //报废物资数量
    private Long disabledNum;
    //可用物资数量
    private Long canUseNum;
}
