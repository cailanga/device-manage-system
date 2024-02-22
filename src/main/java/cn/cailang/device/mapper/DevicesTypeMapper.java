package cn.cailang.device.mapper;

import cn.cailang.base.mapper.BaseMapper;
import cn.cailang.device.domain.DevicesType;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cailang
 * @since 2023-05-10
 */
public interface DevicesTypeMapper extends BaseMapper<DevicesType> {
    /**
     * 获取产品类型数据（一级类型）
     * @return 产品类型数据
     */
    List<DevicesType> selectFirstTypes();
}
