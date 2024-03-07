package cn.cailang.statistics.service;

import cn.cailang.base.utils.PageList;
import cn.cailang.statistics.Query.DeviceShowQuery;
import cn.cailang.statistics.vo.DeviceDataChangeVO;
import cn.cailang.statistics.vo.DevicePriceChangeVO;
import cn.cailang.statistics.vo.DeviceTotalInfoVO;

import java.util.List;

public interface IDeviceInfoShowService {
    PageList getDeviceCountsWithTypeName(DeviceShowQuery query);

    DeviceTotalInfoVO getTotalDataInfo();

    DeviceDataChangeVO getDeviceDataChangeVO(DeviceShowQuery query);

    List<DevicePriceChangeVO> getDevicePriceChangeVO(Long deviceId);

    PageList<List<String>> getDeviceInfo(DeviceShowQuery deviceShowQuery);
}
