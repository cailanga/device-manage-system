package cn.cailang.auth.service.impl;

import cn.cailang.auth.domain.Menu;
import cn.cailang.auth.mapper.MenuMapper;
import cn.cailang.auth.service.IMenuService;
import cn.cailang.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: MenuServiceImpl
 * @Description: 菜单业务层
 * @Author: 3299903308@qq.com
 * @Date: 2023/4/28 14:11
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
