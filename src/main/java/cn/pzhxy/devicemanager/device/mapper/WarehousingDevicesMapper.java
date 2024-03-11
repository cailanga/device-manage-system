package cn.pzhxy.devicemanager.device.mapper;

import cn.pzhxy.devicemanager.base.mapper.BaseMapper;
import cn.pzhxy.devicemanager.device.domain.WarehousingDevices;
import cn.pzhxy.devicemanager.device.query.WarehousingDevicesQuery;


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
