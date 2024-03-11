package cn.pzhxy.devicemanager.org.domain;

import cn.pzhxy.devicemanager.base.domain.BaseDomain;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: Department
 * @Description: 部门
 * @Author: 2026972757@qq.com
 * @Date: 2024/2/23 14:53
 * @Version 1.0
 **/
@ApiModel(value = "部门")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department extends BaseDomain {
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "简介描述")
    private String intro;
    @ApiModelProperty(value ="创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
    @ApiModelProperty(value = "部门管理id")
    private Long managerId;
    @ApiModelProperty(value = "部门管理")
    private Employee manager;
    @ApiModelProperty(value = "父级部门id")
    private Long parentId;
    @ApiModelProperty(value = "父级部门")
    private Department parent;
    @ApiModelProperty(value = "部门路径")
    private String path;
    @ApiModelProperty(value = "状态")
    private Integer state;

    @ApiModelProperty(value = "子部门")
    private List<Department> children;
}
