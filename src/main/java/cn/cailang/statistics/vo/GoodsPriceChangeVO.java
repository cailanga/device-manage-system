package cn.cailang.statistics.vo;

import lombok.Data;

/**
 * @ClassName: DevicePriceChangeVO
 * @Description: 物资价格变化
 * @Author: cailang
 * @Date: 2024/3/4 9:25
 * @Version 1.0
 **/
@Data
public class GoodsPriceChangeVO {
    private String date;
    private Double price;
}
