package cn.pzhxy.devicemanager.sys.service;

import cn.pzhxy.devicemanager.org.domain.Employee;
import cn.pzhxy.devicemanager.sys.domain.Backup;
import cn.pzhxy.devicemanager.base.service.IBaseService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lvjiaxin
 * @since 2024-02-27
 */
public interface IBackupService extends IBaseService<Backup> {
    void backup(Backup backup,String backupType);

    Boolean restore(Long id, Employee employee);
}
