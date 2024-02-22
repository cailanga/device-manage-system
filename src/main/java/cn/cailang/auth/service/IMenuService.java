package cn.cailang.auth.service;

import cn.cailang.auth.domain.Menu;
import cn.cailang.base.service.IBaseService;
import java.util.List;

public interface IMenuService extends IBaseService<Menu> {
    /**
     * 根据员工id获取菜单树
     * @param employeeId 员工id
     * @return 菜单集合
     */
    List<Menu> selectMenuTreeByEmployeeId(Long employeeId);
}
