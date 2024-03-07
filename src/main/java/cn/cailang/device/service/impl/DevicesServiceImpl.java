package cn.cailang.device.service.impl;

import cn.cailang.base.service.impl.BaseServiceImpl;
import cn.cailang.device.domain.Devices;
import cn.cailang.device.mapper.DevicesMapper;
import cn.cailang.device.service.IDevicesService;
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
