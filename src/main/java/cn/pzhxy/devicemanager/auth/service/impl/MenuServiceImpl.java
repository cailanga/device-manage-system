package cn.pzhxy.devicemanager.auth.service.impl;

import cn.pzhxy.devicemanager.auth.domain.Menu;
import cn.pzhxy.devicemanager.auth.mapper.MenuMapper;
import cn.pzhxy.devicemanager.auth.service.IMenuService;
import cn.pzhxy.devicemanager.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: MenuServiceImpl
 * @Description: 菜单业务层
 * @Author: 2026972757@qq.com
 * @Date: 2024/2/28 14:11
 * @Version 1.0
 **/
@Service
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> selectMenuTreeByEmployeeId(Long employeeId) {
        return menuMapper.selectMenuTreeByEmployeeId(employeeId);
    }
}
