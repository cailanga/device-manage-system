package cn.pzhxy.devicemanager.sys.controller;

import cn.pzhxy.devicemanager.auth.annotation.JiaXinPermission;
import cn.pzhxy.devicemanager.base.utils.LoginUtil;
import cn.pzhxy.devicemanager.org.domain.Employee;
import cn.pzhxy.devicemanager.sys.domain.BackupOperatorLog;
import cn.pzhxy.devicemanager.sys.service.IBackupOperatorLogService;
import cn.pzhxy.devicemanager.sys.service.IBackupService;
import cn.pzhxy.devicemanager.sys.domain.Backup;
import cn.pzhxy.devicemanager.sys.query.BackupQuery;
import cn.pzhxy.devicemanager.base.utils.AjaxResult;
import cn.pzhxy.devicemanager.base.utils.PageList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
@JiaXinPermission(name = "系统备份管理权限",description = "系统备份管理权限")
@Api(value = "系统备份管理",description="系统备份相关功能")
@RestController
@RequestMapping("/backup")
public class BackupController {
    @Autowired
    public IBackupService backupService;
    @Autowired
    public IBackupOperatorLogService backupOperatorLogService;

    @Value("${backup.filePath}")
    private String backupFilePath;


    /**
     * 保存和修改公用的
     * @param backup  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody Backup backup){
        try {
            if( backup.getId()!=null){
                    backupService.update(backup);
            }
            else{
                    backupService.insert(backup);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("保存对象失败！"+e.getMessage());
        }
    }

    /**
     * 手动备份
     * @param backup  传递的实体
     * @return Ajaxresult转换结果
     */
    @ApiOperation("手动备份")
    @JiaXinPermission(name = "手动备份权限",description = "手动备份权限")
    @PutMapping("/manual")
    public AjaxResult back(@RequestBody Backup backup){
        try {
            backupService.backup(backup,"手动备份");
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("保存对象失败！"+e.getMessage());
        }
    }
    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @ApiOperation("删除备份")
    @JiaXinPermission(name = "删除备份权限",description = "删除备份权限")
    @DeleteMapping(value="/{id}")
    public AjaxResult delete(@PathVariable("id") Long id, HttpServletRequest request){
        try {
            Backup backup = backupService.selectById(id);
            backupService.deleteById(id);

            String backupFileName = backup.getBackupFileName();
            // 获取类路径下的备份文件夹的绝对路径
            Path fileToDeletePath = Paths.get(backupFilePath, backupFileName);
            // 删除文件
            Files.deleteIfExists(fileToDeletePath);

            Employee userInfo = LoginUtil.getUserInfo(request);
            BackupOperatorLog backupOperatorLog = new BackupOperatorLog();
            backupOperatorLog.setTime(new Date());
            backupOperatorLog.setDescription(userInfo.getUsername()+"删除了备份文件："+backupFileName);
            backupOperatorLog.setOperatorId(userInfo.getId());
            backupOperatorLog.setOperatorName("删除备份文件");
            backupOperatorLogService.insert(backupOperatorLog);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("删除对象失败！"+e.getMessage());
        }
    }

    /**
     * 备份还原
     * @param id
     * @return
     */
    @ApiOperation("恢复备份")
    @JiaXinPermission(name = "恢复备份权限",description = "恢复备份权限")
    @PostMapping(value="/restore/{id}")
    public AjaxResult restore(@PathVariable("id") Long id,HttpServletRequest request){
        try {
            Boolean flag = backupService.restore(id, LoginUtil.getUserInfo(request));
            if (!flag){
                return AjaxResult.me().setSuccess(false).setMessage("备份文件恢复失败！");
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("备份还原失败！"+e.getMessage());
        }
    }

    /**
    * 批量删除对象信息
    * @param ids
    * @return
    */
    @PatchMapping()
    public AjaxResult batchDelete(@RequestBody List<Long> ids){
        try {
                backupService.batchDelete(ids);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("删除对象失败！"+e.getMessage());
        }
    }
	
    //获取用户
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id")Long id)
    {
        try {
            Backup backup = backupService.selectById(id);
            return AjaxResult.me().setResultObject(backup);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取一个失败！"+e.getMessage());
        }
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @GetMapping
    public AjaxResult list(){

        try {
            List< Backup> list = backupService.selectAll();
            return AjaxResult.me().setResultObject(list);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取所有失败！"+e.getMessage());
        }
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation("备份关键字分页查询")
    @JiaXinPermission(name = "备份关键字分页查询权限",description = "备份关键字分页查询权限")
    @PostMapping
    public AjaxResult json(@RequestBody BackupQuery query)
    {
        try {
            PageList<Backup> pageList = backupService.pageList(query);
            return AjaxResult.me().setResultObject(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取分页数据失败！"+e.getMessage());
        }
    }
}
