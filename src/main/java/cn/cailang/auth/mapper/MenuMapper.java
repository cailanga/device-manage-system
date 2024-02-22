package cn.cailang.auth.mapper;

import cn.cailang.auth.domain.Menu;
import cn.cailang.base.mapper.BaseMapper;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据名称查询
     * @param name 菜单名称
     * @return 菜单信息
     */
    Menu selectByName(String name);

    /**
     * 根据标识查询
     * @param sn 菜单标识
     * @return 菜单信息
     */
    Menu selectByUrl(String sn);


    /**
     * 根据员工id获取菜单树
     * @param employeeId 员工id
     * @return 菜单集合
     */
    List<Menu> selectMenuTreeByEmployeeId(Long employeeId);

}
