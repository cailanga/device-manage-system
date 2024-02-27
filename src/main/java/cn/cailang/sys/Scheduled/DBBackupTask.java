package cn.cailang.sys.Scheduled;

import cn.cailang.sys.domain.Backup;
import cn.cailang.sys.domain.SysScheduledCron;
import cn.cailang.sys.mapper.SysScheduledCronMapper;
import cn.cailang.sys.service.IBackupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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