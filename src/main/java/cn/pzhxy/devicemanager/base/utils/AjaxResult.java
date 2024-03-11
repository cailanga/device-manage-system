package cn.pzhxy.devicemanager.base.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: AjaxResult
 * @Description: controller 返回结果
 * @Author: 2026972757@qq.com
 * @Date: 2024/2/23 16:16
 * @Version 1.0
 **/
@ApiModel("后台返回结果对象")
public class AjaxResult {
    // 操作是否成功状态 true:成功 false:失败
    @ApiModelProperty(value = "操作是否成功状态 true:成功 false:失败")
    private Boolean success = true;
    // 操作成功或失败原因
    @ApiModelProperty(value="操作成功或失败原因")
    private String message = "操作成功";
    // 查询的结果
    @ApiModelProperty(value = "返回的数据")
    private Object resultObject;

    public static AjaxResult me(){
        return new AjaxResult();
    }

    public Boolean getSuccess() {
        return success;
    }

    public AjaxResult setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public AjaxResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getResultObject() {
        return resultObject;
    }

    public AjaxResult setResultObject(Object resultObject) {
        this.resultObject = resultObject;
        return this;
    }
}
