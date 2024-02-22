package cn.cailang.device.mapper;

import cn.cailang.base.mapper.BaseMapper;
import cn.cailang.device.domain.WarehousingDevices;
import cn.cailang.device.query.WarehousingDevicesQuery;


import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */
public interface WarehousingDevicesMapper extends BaseMapper<WarehousingDevices> {
 //批量入库
    void batchWarehousing(List<Long> ids);

    Long queryTotalForChecking(WarehousingDevicesQuery query);

    List<WarehousingDevices> queryPageListForChecking(WarehousingDevicesQuery query);
}
