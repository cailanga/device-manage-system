package cn.pzhxy.devicemanager.device.service;

import cn.pzhxy.devicemanager.base.service.IBaseService;
import cn.pzhxy.devicemanager.device.domain.Devices;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface IDevicesService extends IBaseService<Devices> {

    List<Devices> selectByTypeId(Long id);
}
