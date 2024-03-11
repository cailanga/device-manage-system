package cn.pzhxy.devicemanager.sys.domain;


import lombok.Data;

@Data
public class SysScheduledCron {

    private Integer cronId;//主键

    private String cronKey;//定时任务完整类名

    private String cronExpression;//cron表达式

    private String taskExplain;//任务描述

    private Integer taskStatus;//状态: {1:正常,2:停用}
}
