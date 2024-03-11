package cn.pzhxy.devicemanager.device.mapper;

import cn.pzhxy.devicemanager.base.mapper.BaseMapper;
import cn.pzhxy.devicemanager.device.domain.Devices;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */
public interface DevicesMapper extends BaseMapper<Devices> {

    List<Devices> selectByTypeId(Long typeId);
}
