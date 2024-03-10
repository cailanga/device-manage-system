package cn.cailang.statistics.controller;

import cn.cailang.auth.annotation.JiaXinPermission;
import cn.cailang.base.utils.AjaxResult;
import cn.cailang.base.utils.PageList;
import cn.cailang.device.domain.Devices;
import cn.cailang.statistics.Query.DeviceShowQuery;
import cn.cailang.statistics.service.IDeviceInfoShowService;
import cn.cailang.statistics.vo.DeviceDataChangeVO;
import cn.cailang.statistics.vo.DevicePriceChangeVO;
import cn.cailang.statistics.vo.DeviceTotalInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: DeviceInfoShowController
 * @Description: 设备数据展示
 * @Author: cailang
 * @Date: 2024/2/28 17:34
 * @Version 1.0
 **/
@JiaXinPermission(name = "设备统计信息查看权限",description = "设备统计信息查看权限")
@Api(value = "设备统计信息",description="设备统计信息相关的功能")
@RestController
@RequestMapping("/deviceShow")
public class DeviceInfoShowController {
    @Autowired
    private IDeviceInfoShowService deviceInfoShowService;

    /**
     * 获取设备类型数量统计信息
     * @param query
     * @return
     */
    @ApiOperation("设备类型统计查看")
    @JiaXinPermission(name = "设备类型统计查看权限",description = "设备类型统计查看权限")
    @PostMapping("/countsWithType")
    public AjaxResult getDeviceCountsWithTypeName(@RequestBody DeviceShowQuery query) {
        PageList deviceCountsWithTypeName = deviceInfoShowService.getDeviceCountsWithTypeName(query);
        return AjaxResult.me().setResultObject(deviceCountsWithTypeName);
    }

    /**
     * 获取设备总统计信息
     * @return
     */
    @ApiOperation("设备数量总统计信息查看")
    @JiaXinPermission(name = "设备数量总统计信息查看权限",description = "设备数量总统计信息查看权限")
    @GetMapping("/totalDataInfo")
    public AjaxResult totalDataInfo() {
        DeviceTotalInfoVO deviceTotalInfo = deviceInfoShowService.getTotalDataInfo();
        return AjaxResult.me().setResultObject(deviceTotalInfo);
    }

    /**
     * 获取设备各种情况趋势的信息
     * @return
     */
    @ApiOperation("设备各状态数量查看")
    @JiaXinPermission(name = "设备各状态数量查看权限",description = "设备各状态数量查看权限")
    @PostMapping("/countsWithDevice")
    public AjaxResult countsWithDevice(@RequestBody @Validated DeviceShowQuery query) {
        DeviceDataChangeVO deviceDataChangeVO = deviceInfoShowService.getDeviceDataChangeVO(query);
        return AjaxResult.me().setResultObject(deviceDataChangeVO);
    }

    /**
     * 获取设备价格变化趋势的信息
     * @return
     */
    @ApiOperation("设备价格趋势查看")
    @JiaXinPermission(name = "设备价格趋势查看权限",description = "设备价格趋势查看权限")
    @PostMapping("/devicePriceChange/{deviceId}")
    public AjaxResult devicePriceChange(@PathVariable(value = "deviceId") Long deviceId) {
        List<DevicePriceChangeVO> devicePriceChangeVOList = deviceInfoShowService.getDevicePriceChangeVO(deviceId);
        return AjaxResult.me().setResultObject(devicePriceChangeVOList);
    }

    /**
     * 获取设备信息
     * @return
     */
    @ApiOperation("近期设备信息查看")
    @JiaXinPermission(name = "近期设备信息查看权限",description = "近期设备信息查看权限")
    @PostMapping("/device")
    public AjaxResult deviceInfo(@RequestBody DeviceShowQuery deviceShowQuery) {
        PageList<List<String>> devices = deviceInfoShowService.getDeviceInfo(deviceShowQuery);
        return AjaxResult.me().setResultObject(devices);
    }

}
