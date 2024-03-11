package cn.pzhxy.devicemanager.sys.Scheduled;

import cn.pzhxy.devicemanager.sys.domain.Backup;
import cn.pzhxy.devicemanager.sys.domain.SysScheduledCron;
import cn.pzhxy.devicemanager.sys.mapper.SysScheduledCronMapper;
import cn.pzhxy.devicemanager.sys.service.IBackupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 创建定时任务
 */
@Component
@Scope("prototype")
//@Slf4j
public class DBBackupTask implements ScheduledOfTask {
    @Autowired
    private SysScheduledCronMapper sysScheduledCronMapper;
    @Autowired
    private IBackupService backupService;
    @Override
    public void execute(String cronKey) {
        SysScheduledCron scheduledCron = sysScheduledCronMapper.findByCronKey(cronKey);
        if (SysScheduledStatusEnum.DISABLED.getCode().equals(scheduledCron.getTaskStatus())) {
            // 任务是禁用状态
            return;
        }
        System.out.println("备份");
        Backup backup = new Backup();
        //-1代表 自动系统备份
        backup.setOperatorId(-1L);
        backup.setDescription("系统定时任务备份,执行规则："+scheduledCron.getCronExpression());
        backupService.backup(backup,"系统备份");
    }
}