package cn.pzhxy.devicemanager.statistics.service;

import cn.pzhxy.devicemanager.base.utils.PageList;
import cn.pzhxy.devicemanager.statistics.Query.DeviceShowQuery;
import cn.pzhxy.devicemanager.statistics.vo.DeviceDataChangeVO;
import cn.pzhxy.devicemanager.statistics.vo.DevicePriceChangeVO;
import cn.pzhxy.devicemanager.statistics.vo.DeviceTotalInfoVO;

import java.util.List;

public interface IDeviceInfoShowService {
    PageList getDeviceCountsWithTypeName(DeviceShowQuery query);

    DeviceTotalInfoVO getTotalDataInfo();

    DeviceDataChangeVO getDeviceDataChangeVO(DeviceShowQuery query);

    List<DevicePriceChangeVO> getDevicePriceChangeVO(Long deviceId);

    PageList<List<String>> getDeviceInfo(DeviceShowQuery deviceShowQuery);
}
