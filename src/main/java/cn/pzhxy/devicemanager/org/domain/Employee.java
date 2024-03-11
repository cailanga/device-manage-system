package cn.pzhxy.devicemanager.org.domain;

import cn.pzhxy.devicemanager.base.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: Employee
 * @Description: TODO
 * @Author: 2026972757@qq.com
 * @Date: 2024/2/24 16:27
 * @Version 1.0
 **/
@ApiModel("员工信息")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends BaseDomain {
    @ApiModelProperty(value = "员工姓名")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "头像地址")
    private String headImage;
    @ApiModelProperty(value = "年龄")
    private Integer age;
    @ApiModelProperty(value = "部门信息")
    private Department department;
    @ApiModelProperty(value = "部门id")
    private Long departmentId;
}
