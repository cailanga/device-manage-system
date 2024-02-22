package cn.cailang.device.service;

import cn.cailang.base.service.IBaseService;
import cn.cailang.base.utils.PageList;
import cn.cailang.device.domain.WarehousingDevices;
import cn.cailang.device.dto.DevicesHandleDTO;
import cn.cailang.device.query.WarehousingDevicesQuery;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface IWarehousingDevicesService extends IBaseService<WarehousingDevices> {

    void batchWarehousing(List<Long> ids, HttpServletRequest request);

    void handle(DevicesHandleDTO dto, HttpServletRequest request);

    PageList<WarehousingDevices> pageListForChecking(WarehousingDevicesQuery query);
}
