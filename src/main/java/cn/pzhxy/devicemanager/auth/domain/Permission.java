package cn.pzhxy.devicemanager.auth.domain;

import cn.pzhxy.devicemanager.base.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName: Promission
 * @Description: 权限类
 * @Author: 2026972757@qq.com
 * @Date: 2024/2/25 19:18
 * @Version 1.0
 **/
@ApiModel("权限")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission extends BaseDomain {
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "描述")
    private String descs;
    @ApiModelProperty(value = "url路径")
    private String url;
    @ApiModelProperty(value = "唯一标识")
    private String sn;
    @ApiModelProperty(value = "父级权限id")
    private Long parentId;
    @ApiModelProperty(value = "父级权限")
    private Permission parent;
    @ApiModelProperty(value = "子权限")
    private List<Permission> children;
}
