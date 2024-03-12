package cn.pzhxy.devicemanager.device.dto;

import lombok.Data;

/**
 * @ClassName: DeviceDeptDTO
 * @Description: TODO
 * @Author: cailang
 * @Date: 2024/3/12 16:27
 * @Version 1.0
 **/
@Data
public class DeviceDeptDTO {
    private Long deviceId;
    private Long deptId;
    private String deptPath;
}
