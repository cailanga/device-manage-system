package cn.cailang.device.mapper;

import cn.cailang.base.mapper.BaseMapper;
import cn.cailang.device.domain.Devices;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */
public interface DevicesMapper extends BaseMapper<Devices> {

    List<Devices> selectByTypeId(Long typeId);
}
