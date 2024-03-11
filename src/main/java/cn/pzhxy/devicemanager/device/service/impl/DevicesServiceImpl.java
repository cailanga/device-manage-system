package cn.pzhxy.devicemanager.device.service.impl;

import cn.pzhxy.devicemanager.base.service.impl.BaseServiceImpl;
import cn.pzhxy.devicemanager.device.domain.Devices;
import cn.pzhxy.devicemanager.device.mapper.DevicesMapper;
import cn.pzhxy.devicemanager.device.service.IDevicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 */
@Service
public class DevicesServiceImpl extends BaseServiceImpl<Devices> implements IDevicesService {

    @Autowired
    private DevicesMapper devicesMapper;
    @Override
    public List<Devices> selectByTypeId(Long id) {

        return devicesMapper.selectByTypeId(id);
    }
}
