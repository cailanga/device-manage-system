package cn.pzhxy.devicemanager.base.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: BaseDomain
 * @Description: 基础实体
 * @Author: 2026972757@qq.com
 * @Date: 2024/3/8 14:27
 * @Version 1.0
 **/
@Data
public class BaseDomain {
    @ApiModelProperty("主键id")
    private Long id;
}
