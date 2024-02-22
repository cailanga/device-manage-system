package cn.cailang.auth.domain;

import cn.cailang.base.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName: Menu
 * @Description: 菜单
 * @Author: 3299903308@qq.com
 * @Date: 2023/4/28 13:43
 * @Version 1.0
 **/
@ApiModel("菜单")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu extends BaseDomain {
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "路径")
    private String url;
    @ApiModelProperty(value = "图标")
    private String icon;
    @ApiModelProperty(value = "父级菜单ID")
    private Long parentId;
    @ApiModelProperty(value = "父级菜单对象")
    private Menu parent;
    @ApiModelProperty(value = "子菜单")
    private List<Menu> children;

}
