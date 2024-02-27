package cn.cailang.sys.service;

import cn.cailang.org.domain.Employee;
import cn.cailang.sys.domain.Backup;
import cn.cailang.base.service.IBaseService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cailang
 * @since 2024-02-27
 */
public interface IBackupService extends IBaseService<Backup> {
    void backup(Backup backup,String backupType);

    Boolean restore(Long id, Employee employee);
}
