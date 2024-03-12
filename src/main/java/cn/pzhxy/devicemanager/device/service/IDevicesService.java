package cn.pzhxy.devicemanager.device.service;

import cn.pzhxy.devicemanager.base.service.IBaseService;
import cn.pzhxy.devicemanager.base.utils.PageList;
import cn.pzhxy.devicemanager.device.domain.Devices;
import cn.pzhxy.devicemanager.device.dto.DeviceCanUseDeptDTO;
import cn.pzhxy.devicemanager.device.dto.DeviceDeptDTO;
import cn.pzhxy.devicemanager.device.query.DevicesQuery;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface IDevicesService extends IBaseService<Devices> {

    List<Devices> selectByTypeId(Long id);

    void setCanUseDept(DeviceCanUseDeptDTO dto, HttpServletRequest request);

    PageList<Devices> pageListByDept(DevicesQuery query,HttpServletRequest request);

    List<DeviceDeptDTO> selectDeptIdsByDeviceId(Long deviceId);
}
