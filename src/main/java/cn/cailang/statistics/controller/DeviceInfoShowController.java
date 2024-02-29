package cn.cailang.statistics.controller;

import cn.cailang.base.utils.AjaxResult;
import cn.cailang.base.utils.PageList;
import cn.cailang.statistics.Query.DeviceShowQuery;
import cn.cailang.statistics.service.IDeviceInfoShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: DeviceInfoShowController
 * @Description: 设备数据展示
 * @Author: cailang
 * @Date: 2024/2/28 17:34
 * @Version 1.0
 **/
@RestController
@RequestMapping("/deviceShow")
public class DeviceInfoShowController {
    @Autowired
    private IDeviceInfoShowService deviceInfoShowService;

    @PostMapping("/countsWithType")
    public AjaxResult getDeviceCountsWithTypeName(@RequestBody DeviceShowQuery query) {
        PageList deviceCountsWithTypeName = deviceInfoShowService.getDeviceCountsWithTypeName(query);
        return AjaxResult.me().setResultObject(deviceCountsWithTypeName);
    }
}
