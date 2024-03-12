package cn.pzhxy.devicemanager.device.dto;

import lombok.Data;

import java.util.List;

/**
 * @ClassName: DeviceCanUseDeptDTO
 * @Description: TODO
 * @Author: cailang
 * @Date: 2024/3/12 16:26
 * @Version 1.0
 **/
@Data
public class DeviceCanUseDeptDTO {
    private Long deviceId;
    private List<DeviceDeptDTO> deviceDepts;
}
