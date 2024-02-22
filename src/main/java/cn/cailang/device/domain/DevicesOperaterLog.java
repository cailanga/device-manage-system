package cn.cailang.device.domain;

import cn.cailang.base.domain.BaseDomain;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName: OperaterLog
 * @Description: TODO
 * @Author: cailang
 * @Date: 2024/2/8 16:53
 * @Version 1.0
 **/
@Data
public class DevicesOperaterLog extends BaseDomain {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String type;
    private String description;
    private Long operatorId;
    private String operatorName;
    private Long originId;
    private String originName;
    private Long deviceId;
    private String deviceName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
