package cn.pzhxy.devicemanager.statistics.vo;

import lombok.Data;

/**
 * @ClassName: DevicePriceChangeVO
 * @Description: 设备价格变化
 * @Author: lvjiaxin
 * @Date: 2024/3/4 9:25
 * @Version 1.0
 **/
@Data
public class DevicePriceChangeVO {
    private String date;
    private Double price;
}
