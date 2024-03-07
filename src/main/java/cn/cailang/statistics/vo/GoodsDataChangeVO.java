package cn.cailang.statistics.vo;

import lombok.Data;

import java.util.List;

/**
 * @ClassName: DeviceDataChangeVO
 * @Description: 物资各状态变化趋势
 * @Author: cailang
 * @Date: 2024/3/4 9:20
 * @Version 1.0
 **/
@Data
public class GoodsDataChangeVO {
    private List<String> timeArray;
    private List<List<Long>> data;
}
