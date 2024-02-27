package cn.cailang.sys.Scheduled;

import cn.cailang.base.utils.ClassUtils;
import cn.cailang.sys.domain.SysScheduledCron;
import cn.cailang.sys.mapper.SysScheduledCronMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * 定时任务配置类
 */
/*
@Configuration
public class ScheduledConfig implements SchedulingConfigurer {

    @Autowired
    private ApplicationContext context;
    @Autowired
    private SysScheduledCronMapper sysScheduledCronMapper;
    @Autowired
    private TaskScheduler taskScheduler;

    private final Map<String, ScheduledFuture<?>> scheduledTasks = new ConcurrentHashMap<>();
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
//        taskScheduler = scheduledTaskRegistrar.getScheduler();
        for (SysScheduledCron sysScheduledCron : sysScheduledCronMapper.findAll()) {
            System.out.println(sysScheduledCron);
            Class<?> clazz;
            Object task;
            try {
                System.out.println("类名："+ClassUtils.getCorrectClassPath(sysScheduledCron.getCronKey()));
                clazz = Class.forName(ClassUtils.getCorrectClassPath(sysScheduledCron.getCronKey()));
                task = context.getBean(clazz);
            } catch (ClassNotFoundException e) {
                throw new IllegalArgumentException("sys_task_cron表数据" + sysScheduledCron.getCronKey() + "有误", e);
            } catch (BeansException e) {
                throw new IllegalArgumentException(sysScheduledCron.getCronKey() + "未纳入到spring管理", e);
            }
            Assert.isAssignable(ScheduledOfTask.class, task.getClass(), "定时任务类必须实现ScheduledOfTask接口");
            // 可以通过改变数据库数据进而实现动态改变执行周期
//            scheduledTaskRegistrar.addTriggerTask(((Runnable) task),
//                    triggerContext -> {
//                        String cronExpression = sysScheduledCronMapper.findByCronKey(sysScheduledCron.getCronKey()).getCronExpression();
//                        System.out.println("111-"+cronExpression);
//                        return new CronTrigger(cronExpression).nextExecutionTime(triggerContext);
//                    }
//            );
            ScheduledFuture<?> future = taskScheduler.schedule((Runnable) task, new CronTrigger(sysScheduledCron.getCronExpression()));
            scheduledTasks.put(sysScheduledCron.getCronKey(), future);
        }

    }
    public void addTask(SysScheduledCron sysScheduledCron) {
        try {
            Class<?> clazz = Class.forName(ClassUtils.getCorrectClassPath(sysScheduledCron.getCronKey()));
            Runnable task = (Runnable) context.getBean(clazz);
            ScheduledFuture<?> future = taskScheduler.schedule(task, new CronTrigger(sysScheduledCron.getCronExpression()));
            scheduledTasks.put(sysScheduledCron.getCronKey(), future);
        } catch (ClassNotFoundException | BeansException e) {
            // 处理异常
        }
    }
    public void removeTask(String cronKey) {
        ScheduledFuture<?> future = scheduledTasks.remove(cronKey);
        if (future != null) {
            future.cancel(true);
        }
    }
    @Bean
    public TaskScheduler taskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

}*/
@Configuration
@EnableScheduling
public class ScheduledConfig implements SchedulingConfigurer {

    @Autowired
    private ApplicationContext context;
    @Autowired
    private SysScheduledCronMapper sysScheduledCronMapper;

    private TaskScheduler taskScheduler;

    private final Map<String, ScheduledFuture<?>> scheduledTasks = new ConcurrentHashMap<>();

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10); // 根据需要设置合适的线程池大小
        scheduler.setThreadNamePrefix("scheduled-task-");
        scheduler.initialize();
        return scheduler;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskScheduler = this.taskScheduler(); // 确保使用相同的TaskScheduler实例
        taskRegistrar.setTaskScheduler(taskScheduler);

        List<SysScheduledCron> cronTasks = sysScheduledCronMapper.findAll();
        if (cronTasks != null) {
            for (SysScheduledCron sysScheduledCron : cronTasks) {
                addTaskToScheduler(sysScheduledCron);
            }
        }
    }

    private void addTaskToScheduler(SysScheduledCron sysScheduledCron) {
        Class<?> clazz;
        try {
            System.out.println("类名：" + sysScheduledCron.getCronKey());
            clazz = Class.forName(ClassUtils.getCorrectClassPath(sysScheduledCron.getCronKey()));
            ScheduledOfTask task = (ScheduledOfTask) context.getBean(clazz);

            ScheduledFuture<?> future = taskScheduler.schedule(() -> task.execute(sysScheduledCron.getCronKey()),new CronTrigger(sysScheduledCron.getCronExpression()));
            scheduledTasks.put(sysScheduledCron.getCronKey(), future);
        } catch (ClassNotFoundException | BeansException e) {
            // 这里可以添加日志或其他错误处理
            System.out.println("Error adding task to scheduler: " + e.getMessage());
        }
    }

    public void addTask(SysScheduledCron sysScheduledCron) {
        addTaskToScheduler(sysScheduledCron);
    }

    public void removeTask(String cronKey) {
        ScheduledFuture<?> future = scheduledTasks.remove(cronKey);
        if (future != null) {
            future.cancel(true);
        }
    }
}
