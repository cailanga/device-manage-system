package cn.pzhxy.devicemanager.device.service;

import cn.pzhxy.devicemanager.base.service.IBaseService;
import cn.pzhxy.devicemanager.base.utils.PageList;
import cn.pzhxy.devicemanager.device.domain.WarehousingDevices;
import cn.pzhxy.devicemanager.device.dto.DevicesHandleDTO;
import cn.pzhxy.devicemanager.device.query.WarehousingDevicesQuery;

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
