package cn.pzhxy.devicemanager.device.service;

import cn.pzhxy.devicemanager.base.service.IBaseService;
import cn.pzhxy.devicemanager.device.domain.DevicesType;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lvjiaxin
 * @since 2023-05-10
 */
public interface IDevicesTypeService extends IBaseService<DevicesType> {

    /**
     * 获取产品类型数据（一级类型）
     * @return 产品类型数据
     */
    List<DevicesType> selectFirstTypes();
}
