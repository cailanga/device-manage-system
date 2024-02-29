package cn.cailang.statistics.service;

import cn.cailang.base.utils.PageList;
import cn.cailang.statistics.Query.DeviceShowQuery;

import java.util.List;
import java.util.Map;

public interface IDeviceInfoShowService {
    PageList getDeviceCountsWithTypeName(DeviceShowQuery query);
}
