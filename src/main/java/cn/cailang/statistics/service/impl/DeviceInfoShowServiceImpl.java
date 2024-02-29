package cn.cailang.statistics.service.impl;

import cn.cailang.base.utils.PageList;
import cn.cailang.statistics.Query.DeviceShowQuery;
import cn.cailang.statistics.mapper.DeviceShowMapper;
import cn.cailang.statistics.service.IDeviceInfoShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: DeviceInfoShowServiceImpl
 * @Description: TODO
 * @Author: cailang
 * @Date: 2024/2/28 17:36
 * @Version 1.0
 **/
@Service
public class DeviceInfoShowServiceImpl implements IDeviceInfoShowService {
    @Autowired
    private DeviceShowMapper deviceShowMapper;
    @Override
    public PageList getDeviceCountsWithTypeName(DeviceShowQuery query) {
        Long deviceTypeTotal = deviceShowMapper.getDeviceTypeTotal(query);
        Long total = 0L;
        if (deviceTypeTotal!=null) {
            total = deviceTypeTotal;
        }
        List<Map<String, Object>> deviceTypeCountsWithTypeName = deviceShowMapper.getDeviceTypeCountsWithTypeName(query);
        return new PageList<>(total,deviceTypeCountsWithTypeName);
    }
}
