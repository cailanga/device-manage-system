package cn.cailang.statistics.service.impl;

import cn.cailang.base.utils.PageList;
import cn.cailang.device.domain.Devices;
import cn.cailang.statistics.Query.DeviceShowQuery;
import cn.cailang.statistics.mapper.DeviceShowMapper;
import cn.cailang.statistics.service.IDeviceInfoShowService;
import cn.cailang.statistics.vo.DeviceDataChangeStatisticVO;
import cn.cailang.statistics.vo.DeviceDataChangeVO;
import cn.cailang.statistics.vo.DevicePriceChangeVO;
import cn.cailang.statistics.vo.DeviceTotalInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        List<Map<String, Object>> deviceTypeUseCountsWithTypeName = deviceShowMapper.getDeviceTypeUseCountsWithTypeName(query);
        List<Map<String, Object>> deviceTypeCountsWithTypeName = deviceShowMapper.getDeviceTypeCountsWithTypeName(query);
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(deviceTypeCountsWithTypeName);
        arrayList.add(deviceTypeUseCountsWithTypeName);
        return new PageList<>(total,arrayList);
    }

    @Override
    public DeviceTotalInfoVO getTotalDataInfo() {
        //设备总数量
        Long total = deviceShowMapper.selectDeviceTotal();
        //已入库设备数量
        Long inNum = deviceShowMapper.selectDeviceInTotal();
        //未入库设备数量
        Long notInNum = deviceShowMapper.selectDeviceNotInTotal();
        //报废设备数量
        Long disabledTotal = deviceShowMapper.selectDeviceDisabledTotal();
        //可用设备数量
        Long canUseTotal = deviceShowMapper.selectDeviceCanUseTotal();
        DeviceTotalInfoVO deviceTotalInfoVO = new DeviceTotalInfoVO();
        deviceTotalInfoVO.setTotalNum(total);
        deviceTotalInfoVO.setInNum(inNum);
        deviceTotalInfoVO.setNoInNum(notInNum);
        deviceTotalInfoVO.setDisabledNum(disabledTotal);
        deviceTotalInfoVO.setCanUseNum(canUseTotal);
        return deviceTotalInfoVO;
    }

    @Override
    public DeviceDataChangeVO getDeviceDataChangeVO(DeviceShowQuery query) {
        Integer year = query.getYear();
        Integer month = query.getMonth();
        String type = query.getType();
        DeviceDataChangeVO deviceDataChangeVO = new DeviceDataChangeVO();
        ArrayList<List<Long>> data = new ArrayList<>();
        //查询入库设备数量
        List<DeviceDataChangeStatisticVO> statistics = null;
        //查询设备使用量
        List<DeviceDataChangeStatisticVO> usageStatistics = null;
        //查询设备维修量
        List<DeviceDataChangeStatisticVO> disabledStatistics = null;
        //查询设备报废量
        List<DeviceDataChangeStatisticVO>maintainStatistics = null;
        if ("year".equals(type)){
            //按年查询
            //查询入库设备数量
            statistics = deviceShowMapper.getYearlyStatistics(year);
            //查询设备使用量
            usageStatistics = deviceShowMapper.getYearlyUsageStatistics(year);
            //查询设备维修量
            disabledStatistics = deviceShowMapper.getYearlyDisabledStatistics(year);
            //查询设备报废量
            maintainStatistics = deviceShowMapper.getYearlyMaintainStatistics(year);

        } else if ("month".equals(type)) {
            //按月份查询
            //查询入库设备数量
            statistics = deviceShowMapper.getMonthlyStatistics(year);
            //查询设备使用量
            usageStatistics = deviceShowMapper.getMonthlyUsageStatistics(year);
            //查询设备维修量
            disabledStatistics = deviceShowMapper.getMonthlyDisabledStatistics(year);
            //查询设备报废量
            maintainStatistics = deviceShowMapper.getMonthlyMaintainStatistics(year);
        } else if ("quarter".equals(type)) {
            //按季度查询
            //查询入库设备数量
            statistics = deviceShowMapper.getQuarterlyStatistics(year);
            //查询设备使用量
            usageStatistics = deviceShowMapper.getQuarterlyUsageStatistics(year);
            //查询设备维修量
            disabledStatistics = deviceShowMapper.getQuarterlyDisabledStatistics(year);
            //查询设备报废量
            maintainStatistics = deviceShowMapper.getQuarterlyMaintainStatistics(year);
        }else if("day".equals(type)){
            //查询入库设备数量
            statistics = deviceShowMapper.getDailyStatistics(String.format("%04d", year), String.format("%02d", month));
            //查询设备使用量
            usageStatistics = deviceShowMapper.getDailyUsageStatistics(String.format("%04d", year), String.format("%02d", month));
            //查询设备维修量
            disabledStatistics = deviceShowMapper.getDailyDisabledStatistics(String.format("%04d", year), String.format("%02d", month));
            //查询设备报废量
            maintainStatistics = deviceShowMapper.getDailyMaintainStatistics(String.format("%04d", year), String.format("%02d", month));
        }
        assert statistics != null;
        deviceDataChangeVO.setTimeArray(statistics.stream().map(DeviceDataChangeStatisticVO::getDimension).collect(Collectors.toList()));
        data.add(statistics.stream().map(item->{
            if (item.getTotalCount() != null) {
                return item.getTotalCount();
            }else {
                return 0L;
            }
        }).collect(Collectors.toList()));
        assert usageStatistics != null;
        data.add(usageStatistics.stream().map(item->{
            if (item.getTotalCount() != null) {
                return item.getTotalCount();
            }else {
                return 0L;
            }
        }).collect(Collectors.toList()));
        assert disabledStatistics != null;
        data.add(disabledStatistics.stream().map(item->{
            if (item.getTotalCount() != null) {
                return item.getTotalCount();
            }else {
                return 0L;
            }
        }).collect(Collectors.toList()));
        assert maintainStatistics != null;
        data.add(maintainStatistics.stream().map(item->{
            if (item.getTotalCount() != null) {
                return item.getTotalCount();
            }else {
                return 0L;
            }
        }).collect(Collectors.toList()));
        deviceDataChangeVO.setData(data);
        return deviceDataChangeVO;
    }

    @Override
    public List<DevicePriceChangeVO> getDevicePriceChangeVO(Long deviceId) {
        return deviceShowMapper.getDevicePriceChangeVO(deviceId);
    }

    @Override
    public PageList<List<String>> getDeviceInfo(DeviceShowQuery deviceShowQuery) {
        Long total = deviceShowMapper.queryTotal(deviceShowQuery);
        List<Devices> devices = deviceShowMapper.queryPageList(deviceShowQuery);
        List<List<String>> data = new ArrayList<>();
        devices.forEach(device -> {
            if (device!=null){
                ArrayList<String> strings = new ArrayList<>();
                strings.add(device.getTypeId()+"");
                strings.add(device.getName());
                strings.add(device.getPrice()+"");
                SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                // 使用format方法将Date对象格式化成字符串
                String formattedDate = dateTimeFormat.format(device.getCreateTime());
                strings.add(formattedDate);
                data.add(strings);
            }
        });
        return new PageList<>(total, data);
    }
}
