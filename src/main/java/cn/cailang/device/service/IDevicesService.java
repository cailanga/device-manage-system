package cn.cailang.device.service;

import cn.cailang.base.service.IBaseService;
import cn.cailang.device.domain.Devices;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface IDevicesService extends IBaseService<Devices> {

    List<Devices> selectByTypeId(Long id);
}
