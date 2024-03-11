package cn.pzhxy.devicemanager.device.dto;

import lombok.Data;

/**
 * @ClassName: GoodsHandleDTO
 * @Description: TODO
 * @Author: lvjiaxin
 * @Date: 2024/2/8 19:22
 * @Version 1.0
 **/
@Data
public class DevicesHandleDTO {
    private Integer operateTypeId;
    private String description;
    private Long deviceId;
}
