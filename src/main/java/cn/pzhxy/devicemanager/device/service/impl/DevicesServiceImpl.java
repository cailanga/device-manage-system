package cn.pzhxy.devicemanager.device.service.impl;

import cn.pzhxy.devicemanager.base.service.impl.BaseServiceImpl;
import cn.pzhxy.devicemanager.base.utils.LoginUtil;
import cn.pzhxy.devicemanager.base.utils.PageList;
import cn.pzhxy.devicemanager.device.domain.Devices;
import cn.pzhxy.devicemanager.device.dto.DeviceCanUseDeptDTO;
import cn.pzhxy.devicemanager.device.dto.DeviceDeptDTO;
import cn.pzhxy.devicemanager.device.mapper.DeviceDepartmentMapper;
import cn.pzhxy.devicemanager.device.mapper.DevicesMapper;
import cn.pzhxy.devicemanager.device.query.DevicesQuery;
import cn.pzhxy.devicemanager.device.service.IDevicesService;
import cn.pzhxy.devicemanager.org.domain.Department;
import cn.pzhxy.devicemanager.org.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 */
@Service
public class DevicesServiceImpl extends BaseServiceImpl<Devices> implements IDevicesService {

    @Autowired
    private DevicesMapper devicesMapper;
    @Autowired
    private DeviceDepartmentMapper deviceDepartmentMapper;

    @Override
    public List<Devices> selectByTypeId(Long id) {

        return devicesMapper.selectByTypeId(id);
    }

    @Override
    public void setCanUseDept(DeviceCanUseDeptDTO dto, HttpServletRequest request) {
        Employee userInfo = LoginUtil.getUserInfo(request);
        Long deviceId = dto.getDeviceId();
        List<DeviceDeptDTO> deviceDepts = dto.getDeviceDepts();
        //先删除表中的设备部门对应关系
        deviceDepartmentMapper.deleteByDeviceId(deviceId);
        if (deviceDepts.size()>0){
            //要添加新增的数据
            deviceDepts.forEach(deviceDeptDTO -> {
                deviceDeptDTO.setDeviceId(deviceId);
                deviceDepartmentMapper.insert(deviceDeptDTO);
            });
        }
    }

    @Override
    public PageList<Devices> pageListByDept(DevicesQuery query,HttpServletRequest request) {
        Employee userInfo = LoginUtil.getUserInfo(request);
        List<Long> deviceIds = deviceDepartmentMapper.selectDeviceIdByDepartmentId(query,userInfo.getDepartmentId());
        if (deviceIds.size()==0){
            return new PageList<>();
        }
        List<Devices> devices = deviceDepartmentMapper.selectDeviceByDepartmentId(query, userInfo.getDepartmentId());
        return new PageList<>(deviceIds.size()+0L,devices);
    }

    @Override
    public List<DeviceDeptDTO> selectDeptIdsByDeviceId(Long deviceId) {
        List<DeviceDeptDTO> deviceDeptDTOS =  deviceDepartmentMapper.selectDeptIdsByDeviceId(deviceId);
        return deviceDeptDTOS;
    }
}
