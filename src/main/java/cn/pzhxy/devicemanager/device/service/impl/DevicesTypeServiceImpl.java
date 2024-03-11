package cn.pzhxy.devicemanager.device.service.impl;

import cn.pzhxy.devicemanager.base.service.impl.BaseServiceImpl;
import cn.pzhxy.devicemanager.device.domain.DevicesType;
import cn.pzhxy.devicemanager.device.mapper.DevicesTypeMapper;
import cn.pzhxy.devicemanager.device.service.IDevicesTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lvjiaxin
 * @since 2023-05-10
 */
@Service
public class DevicesTypeServiceImpl extends BaseServiceImpl<DevicesType> implements IDevicesTypeService {
    @Autowired
    private DevicesTypeMapper devicesTypeMapper;
    @Override
    public List<DevicesType> selectFirstTypes() {
        return devicesTypeMapper.selectFirstTypes();
    }
}
