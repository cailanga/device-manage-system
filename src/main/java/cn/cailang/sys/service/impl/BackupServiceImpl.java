package cn.cailang.sys.service.impl;

import cn.cailang.base.service.IBaseService;
import cn.cailang.base.service.impl.BaseServiceImpl;
import cn.cailang.org.domain.Employee;
import cn.cailang.sys.domain.Backup;
import cn.cailang.sys.domain.BackupOperatorLog;
import cn.cailang.sys.mapper.BackupMapper;
import cn.cailang.sys.mapper.BackupOperatorLogMapper;
import cn.cailang.sys.service.IBackupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: BackupServiceImpl
 * @Description: TODO
 * @Author: cailang
 * @Date: 2024/2/27 15:58
 * @Version 1.0
 **/
@Service
public class BackupServiceImpl extends BaseServiceImpl<Backup> implements IBackupService {
    private final Environment env;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${backup.filePath}")
    private String backupFilePath;
    @Autowired
    private BackupMapper backupMapper;
    @Autowired
    private BackupOperatorLogMapper backupOperatorLogMapper;

    @Autowired
    public BackupServiceImpl(Environment env) {
        this.env = env;
    }

    @Override
    public void backup(Backup backup, String backupType) {
        // 获取数据库名
        String url = env.getProperty("spring.datasource.url");
        String databaseName = url.substring(url.lastIndexOf("/") + 1, url.indexOf("?"));
        // 构建备份文件名
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String backupFileName = timestamp + "_" + databaseName + ".sql";
        // 构建备份文件路径
        String backupFilePath1 = backupFilePath + backupFileName;
        File backupFile = new File(backupFilePath1);
        if (!backupFile.getParentFile().exists()) {
            backupFile.getParentFile().mkdirs();
        }
        // 构建备份命令
//        String backupCommand = String.format("cmd /c mysqldump -u%s -p%s %s -r%s", username, password, databaseName, backupFilePath1);
//        String backupCommand = String.format("cmd /c mysqldump -u%s -p%s --single-transaction --quick --lock-tables=false %s -r\"%s\"",
//                username, password, databaseName, backupFilePath1);
        String[] backupCommand = new String[]{"cmd","/c","\"C:\\Program Files\\MySQL\\MySQL Server 5.7\\bin\\mysqldump\"", "-u"+username,"-p"+password,databaseName,"-r"+backupFilePath1};
        System.out.println("备份命令："+backupCommand);
        try {
//            Process process = null;
            // 执行备份命令
//            process = Runtime.getRuntime().exec(backupCommand);
//            process.waitFor();
            ProcessBuilder processBuilder = new ProcessBuilder(backupCommand);
            processBuilder.redirectErrorStream(true); // 将错误输出和标准输出合并
            Process process = processBuilder.start();
            int processComplete = process.waitFor();
            // 读取输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            if (processComplete == 0) {
                System.out.println("Database restored successfully.");
            } else {
                System.out.println("Database restoration failed with exit code " + processComplete);
            }
            //保存到数据库
            backup.setBackupTime(new Date());
            backup.setBackupFileName(backupFileName);
            backupMapper.insert(backup);
            //记录日志
            BackupOperatorLog backupOperatorLog = new BackupOperatorLog();
            backupOperatorLog.setOperatorId(backup.getOperatorId());
            backupOperatorLog.setTime(new Date());
            backupOperatorLog.setOperatorName(backupType);
            backupOperatorLog.setDescription(backup.getDescription());
            backupOperatorLogMapper.insert(backupOperatorLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boolean restore(Long id, Employee userInfo) {
        //根据备份文件进行数据恢复
        Backup backup = backupMapper.selectById(id);
        String backupFileName = backup.getBackupFileName();
        File file = new File(backupFilePath + backupFileName);
        String absolutePath = file.getAbsolutePath();

        Boolean flag = false;
        // 获取数据库名
        String url = env.getProperty("spring.datasource.url");
        String databaseName = url.substring(url.lastIndexOf("/") + 1, url.indexOf("?"));
        // MySQL命令行工具的恢复数据库命令
//        String[] executeCmd = new String[]{"cmd","/c","C:\\\"Program Files\"\\MySQL\\\"MySQL Server 5.7\"\\bin\\mysql",  "-u" + username, "-p" + password, databaseName,"\"source " + backupFilePath + backupFileName+"\""};
//        String executeCmd = String.format("cmd /c mysql -u%s -p%s %s <%s", username, password,databaseName,  backupFilePath + backupFileName);
//        String executeCmd = String.format("cmd /c C:\\\"Program Files\"\\MySQL\\\"MySQL Server 5.7\"\\bin\\mysql -u%s -p%s %s < %s", username, password, databaseName, absolutePath);
//        String[] executeCmd = new String[]{
//                "cmd",
//                "/c",
//                "C:\\\"Program Files\"\\MySQL\\\"MySQL Server 5.7\"\\bin\\mysql",
//                "-u" + username,
//                "-p" + password,
//                databaseName,
//                "-e",
//                "source "+backupFilePath + backupFileName
//        };
        String mysqlCmd = "C:\\\"Program Files\"\\MySQL\\\"MySQL Server 5.7\"\\bin\\mysql";
        String command = String.format("%s -u%s -p%s %s < %s%s",
                mysqlCmd, username, password, databaseName, backupFilePath, backupFileName);
        String[] executeCmd = new String[] {
                "cmd",
                "/c",
                command
        };

//        System.out.println("恢复命令："+executeCmd);
        //记录操作日志
        BackupOperatorLog backupOperatorLog = new BackupOperatorLog();
        try {
//            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            ProcessBuilder processBuilder = new ProcessBuilder("cmd",
                    "/c",
                    "\"C:\\Program Files\\MySQL\\MySQL Server 5.7\\bin\\mysql\"",
                    "-u" + username,
                    "-p" + password,
                    databaseName,
                    "<",
                    backupFilePath + backupFileName);
            processBuilder.redirectErrorStream(true); // 将错误输出和标准输出合并
            Process process = processBuilder.start();

//            int processComplete = runtimeProcess.waitFor();
// 读取输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            int processComplete = process.waitFor();
            if (processComplete == 0) {
                System.out.println("Database restored successfully.");
            } else {
                System.out.println("Database restoration failed with exit code " + processComplete);
            }
            if (processComplete == 0) {
                System.out.println("Database restored successfully from " + absolutePath);
                backupOperatorLog.setOperatorName("根据备份文件恢复成功");
                flag = true;
            } else {
                System.out.println("Could not restore the database from " + absolutePath);
                backupOperatorLog.setOperatorName("根据备份文件恢复失败");
            }
        } catch (Exception e) {
            backupOperatorLog.setOperatorName("根据备份文件恢复失败");
            e.printStackTrace();
        }

        backupOperatorLog.setOperatorId(userInfo.getId());
        backupOperatorLog.setTime(new Date());
        backupOperatorLog.setDescription(userInfo.getUsername() + "根据备份文件：" + backupFileName + "进行了数据库恢复");

        backupOperatorLogMapper.insert(backupOperatorLog);
        return flag;
    }
}
