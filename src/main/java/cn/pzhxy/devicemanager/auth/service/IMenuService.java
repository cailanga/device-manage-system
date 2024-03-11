package cn.pzhxy.devicemanager.auth.service;

import cn.pzhxy.devicemanager.auth.domain.Menu;
import cn.pzhxy.devicemanager.base.service.IBaseService;

import java.util.List;

public interface IMenuService extends IBaseService<Menu> {
    /**
     * 根据员工id获取菜单树
     * @param employeeId 员工id
     * @return 菜单集合
     */
    List<Menu> selectMenuTreeByEmployeeId(Long employeeId);
}
