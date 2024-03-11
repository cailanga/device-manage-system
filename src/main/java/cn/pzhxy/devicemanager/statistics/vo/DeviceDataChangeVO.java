package cn.pzhxy.devicemanager.statistics.vo;

import lombok.Data;

import java.util.List;

/**
 * @ClassName: DeviceDataChangeVO
 * @Description: 设备各状态变化趋势
 * @Author: lvjiaxin
 * @Date: 2024/3/4 9:20
 * @Version 1.0
 **/
@Data
public class DeviceDataChangeVO {
    private List<String> timeArray;
    private List<List<Long>> data;
}
