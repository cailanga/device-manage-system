package cn.pzhxy.devicemanager.sys.controller;


import cn.pzhxy.devicemanager.auth.annotation.JiaXinPermission;
import cn.pzhxy.devicemanager.base.utils.AjaxResult;
import cn.pzhxy.devicemanager.base.utils.ClassUtils;
import cn.pzhxy.devicemanager.base.utils.LoginUtil;
import cn.pzhxy.devicemanager.base.utils.PageList;
import cn.pzhxy.devicemanager.org.domain.Employee;
import cn.pzhxy.devicemanager.sys.Scheduled.ScheduledConfig;
import cn.pzhxy.devicemanager.sys.Scheduled.ScheduledOfTask;
import cn.pzhxy.devicemanager.sys.domain.BackupOperatorLog;
import cn.pzhxy.devicemanager.sys.domain.SysScheduledCron;
import cn.pzhxy.devicemanager.sys.mapper.SysScheduledCronMapper;
import cn.pzhxy.devicemanager.sys.query.SysScheduledCronQuery;
import cn.pzhxy.devicemanager.sys.service.IBackupOperatorLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 定时任务控制器
 *
 */
@JiaXinPermission(name = "备份定时任务管理权限",description = "备份定时任务管理权限")
@Api(value = "备份定时任务管理",description="备份定时任务相关的功能")
@Controller
@RequestMapping("/task")
public class SysScheduledCronController {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private ScheduledConfig scheduledConfig;

    @Autowired
    private SysScheduledCronMapper sysScheduledCronMapper;
    @Autowired
    private IBackupOperatorLogService backupOperatorLogService;
    @ApiOperation("定时任务列表查看")
    @JiaXinPermission(name = "定时任务列表查看权限",description = "定时任务列表查看权限")
    @RequestMapping("taskList")
    @ResponseBody
    public AjaxResult taskList(@RequestBody SysScheduledCronQuery query) {
        List<SysScheduledCron> list = sysScheduledCronMapper.queryPageList(query);
        Long total = sysScheduledCronMapper.queryTotal(query);
        return AjaxResult.me().setResultObject(new PageList<>(total,list));
    }

    //编辑Cron表达式
    @ApiOperation("更新定时任务")
    @JiaXinPermission(name = "更新定时任务权限",description = "更新定时任务权限")
    @RequestMapping("updateTask")
    @ResponseBody
    public AjaxResult updateTask(@RequestBody SysScheduledCron sysScheduledCron,HttpServletRequest request) {
        boolean b = jobCronTesting(sysScheduledCron.getCronExpression());
        HashMap<String, Object> map = new HashMap<>();
        boolean flag = false;
        if (!b){
            return AjaxResult.me().setSuccess(false).setMessage("cron表达式错误");
        }else {
            SysScheduledCron byCronKey = sysScheduledCronMapper.findByCronKey(sysScheduledCron.getCronKey());
            int index = sysScheduledCronMapper.updateCronExpression(sysScheduledCron);
            BackupOperatorLog backupOperatorLog = new BackupOperatorLog();
            backupOperatorLog.setOperatorId(LoginUtil.getUserInfo(request).getId());
            backupOperatorLog.setOperatorName("更新CRON表达式");
            backupOperatorLog.setDescription("更新CRON:"+byCronKey.getCronExpression()+"->"+sysScheduledCron.getCronExpression());
            backupOperatorLog.setTime(new Date());
            backupOperatorLogService.insert(backupOperatorLog);
            if(index>0){
                flag=true;
            }
        }
        return AjaxResult.me();
    }

    /**
     * 删除对象信息
     * @param id
     * @return
     */
    @ApiOperation("删除定时任务")
    @JiaXinPermission(name = "删除定时任务权限",description = "删除定时任务权限")
    @DeleteMapping(value="/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id,HttpServletRequest request){
        try {
            SysScheduledCron scheduledCronById = sysScheduledCronMapper.findById(id);
            sysScheduledCronMapper.deleteById(id);
            scheduledConfig.removeTask(scheduledCronById.getCronKey());
            //记录操作日志
            Employee userInfo = LoginUtil.getUserInfo(request);
            BackupOperatorLog backupOperatorLog = new BackupOperatorLog();
            backupOperatorLog.setTime(new Date());

            backupOperatorLog.setDescription(userInfo.getUsername()+"删除了定时任务,任务信息："+scheduledCronById);
            backupOperatorLog.setOperatorId(userInfo.getId());
            backupOperatorLog.setOperatorName("删除定时任务");
            backupOperatorLogService.insert(backupOperatorLog);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("删除对象失败！"+e.getMessage());
        }
    }

    /**
     * 执行定时任务
     */
    @ApiOperation("执行定时任务")
//    @JiaXinPermission(name = "执行定时任务权限",description = "执行定时任务权限")
    @ResponseBody
    @RequestMapping("runTaskCron")
    public AjaxResult runTaskCron(@RequestParam(value = "cronKey") String cronKey,HttpServletRequest request) throws ClassNotFoundException {
        SysScheduledCron byCronKey = sysScheduledCronMapper.findByCronKey(cronKey);
        ((ScheduledOfTask) context.getBean(Class.forName(ClassUtils.getCorrectClassPath(cronKey)))).execute(byCronKey.getCronExpression());
        BackupOperatorLog backupOperatorLog = new BackupOperatorLog();
        backupOperatorLog.setOperatorId(LoginUtil.getUserInfo(request).getId());
        backupOperatorLog.setOperatorName("执行CRON表达式任务");
        backupOperatorLog.setDescription("执行CRON表达式任务："+byCronKey.getCronExpression());
        backupOperatorLog.setTime(new Date());
        backupOperatorLogService.insert(backupOperatorLog);
        return AjaxResult.me();
    }


    /**
     * 启用或禁用定时任务
     */
    @ApiOperation("启用或禁用定时任务")
    @JiaXinPermission(name = "启用或禁用定时任务权限",description = "启用或禁用定时任务权限")
    @ResponseBody
    @RequestMapping("changeStatusTaskCron")
    public AjaxResult changeStatusTaskCron(
            @RequestBody Map<String,String> map,HttpServletRequest request
    ) {
        String taskStatusStr = map.get("taskStatus");
        int taskStatus = Integer.parseInt(taskStatusStr);
        String cronKey = map.get("cronKey");
        int index = sysScheduledCronMapper.updateTaskStatusByCronKey(taskStatus, cronKey);
        boolean flag = false;
        if(index>0){
            flag=true;
            SysScheduledCron sysScheduledCron = sysScheduledCronMapper.findByCronKey(cronKey);
            BackupOperatorLog backupOperatorLog = new BackupOperatorLog();
            backupOperatorLog.setOperatorId(LoginUtil.getUserInfo(request).getId());
            if (1==taskStatus){
                //启用
                backupOperatorLog.setOperatorName("启用CRON表达式");
                backupOperatorLog.setDescription("启用CRON:"+sysScheduledCron.getCronExpression());
            }else {
                //禁用
                backupOperatorLog.setOperatorName("禁用CRON表达式");
                backupOperatorLog.setDescription("禁用CRON:"+sysScheduledCron.getCronExpression());
            }
            backupOperatorLog.setTime(new Date());
            backupOperatorLogService.insert(backupOperatorLog);
        }
        return AjaxResult.me().setResultObject(flag);
    }

    /**
     * 添加定时任务
     */
    @ApiOperation("添加定时任务")
    @JiaXinPermission(name = "添加定时任务权限",description = "添加定时任务权限")
    @ResponseBody
    @RequestMapping("addTask")
    public AjaxResult changeStatusTaskCron(
            @RequestBody SysScheduledCron sysScheduledCron,HttpServletRequest request
    ) {
        boolean flag = false;
        Class<?> clazz;
        Object bean=null;
        try {
            clazz = Class.forName(ClassUtils.getCorrectClassPath(sysScheduledCron.getCronKey()));
            bean = context.getBean(clazz);
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("添加失败，添加的定时任务类不存在");
        }
        if(bean==null){
            flag=false;
            return AjaxResult.me().setSuccess(false).setMessage("添加失败，添加的定时任务类不存在");
        }else {
            SysScheduledCron byCronKey = sysScheduledCronMapper.findByCronKey(sysScheduledCron.getCronKey());
            if (byCronKey!=null&&byCronKey.getCronId()!=null){
                return AjaxResult.me().setSuccess(false).setMessage("添加失败，添加的定时任务已存在");
            }
            int index = sysScheduledCronMapper.insertCron(sysScheduledCron);
            if(index>0){
                flag=true;
                try {
//                    ((ScheduledOfTask) bean).execute(sysScheduledCron.getCronExpression());
                    scheduledConfig.addTask(sysScheduledCron);
                    BackupOperatorLog backupOperatorLog = new BackupOperatorLog();
                    backupOperatorLog.setOperatorId(LoginUtil.getUserInfo(request).getId());
                    //禁用
                    backupOperatorLog.setOperatorName("添加CRON表达式");
                    backupOperatorLog.setDescription("添加CRON:"+sysScheduledCron.getCronExpression());
                    backupOperatorLog.setTime(new Date());
                    backupOperatorLogService.insert(backupOperatorLog);
                } catch (Exception e) {
                    e.printStackTrace();
                    return AjaxResult.me().setSuccess(false).setMessage("添加失败，添加的定时任务类不存在");
                }
            }
        }
        return AjaxResult.me();
    }

    public static boolean jobCronTesting(String cron){

        // cron表达式格式验证
        String str = "0 * * * * ?";
        String regMiao = "([0-9]{1,2}|[0-9]{1,2}\\-[0-9]{1,2}|\\*|[0-9]{1,2}\\/[0-9]{1,2}|[0-9]{1,2}\\,[0-9]{1,2})";
        String regFen = "\\s([0-9]{1,2}|[0-9]{1,2}\\-[0-9]{1,2}|\\*|[0-9]{1,2}\\/[0-9]{1,2}|[0-9]{1,2}\\,[0-9]{1,2})";
        String regShi = "\\s([0-9]{1,2}|[0-9]{1,2}\\-[0-9]{1,2}|\\*|[0-9]{1,2}\\/[0-9]{1,2}|[0-9]{1,2}\\,[0-9]{1,2})";
        String regRi = "\\s([0-9]{1,2}|[0-9]{1,2}\\-[0-9]{1,2}|\\*|[0-9]{1,2}\\/[0-9]{1,2}|[0-9]{1,2}\\,[0-9]{1,2}|\\?|L|W|C)";
        String regYue = "\\s([0-9]{1,2}|[0-9]{1,2}\\-[0-9]{1,2}|\\*|[0-9]{1,2}\\/[0-9]{1,2}|[0-9]{1,2}\\,[0-9]{1,2}|JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)";
        String regZhou = "\\s([1-7]{1}|[1-7]{1}\\-[1-7]{1}|[1-7]{1}\\#[1-7]{1}|\\*|[1-7]{1}\\/[1-7]{1}|[1-7]{1}\\,[1-7]{1}|MON|TUES|WED|THUR|FRI|SAT|SUN|\\?|L|C)";
        String regNian = "(\\s([0-9]{4}|[0-9]{4}\\-[0-9]{4}|\\*|[0-9]{4}\\/[0-9]{4}|[0-9]{4}\\,[0-9]{4})){0,1}";
        String regEx = regMiao + regFen + regShi + regRi + regYue + regZhou + regNian;
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        // 字符串是否与正则表达式相匹配
        boolean rs = matcher.matches();
        System.out.println(rs);
        return rs;
    }

}
