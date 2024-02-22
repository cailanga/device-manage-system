package cn.cailang.device.service.impl;

import cn.cailang.base.service.impl.BaseServiceImpl;
import cn.cailang.device.domain.DevicesType;
import cn.cailang.device.mapper.DevicesTypeMapper;
import cn.cailang.device.service.IDevicesTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cailang
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
