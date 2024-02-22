package cn.cailang.auth.service.impl;

import cn.cailang.auth.domain.Permission;
import cn.cailang.auth.enums.RequestTypeEnum;
import cn.cailang.auth.mapper.PermissionMapper;
import cn.cailang.auth.query.PermissionQuery;
import cn.cailang.auth.service.IPermissionService;
import cn.cailang.base.utils.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName: PermissionServiceImpl
 * @Description: 权限业务层
 * @Author: 3299903308@qq.com
 * @Date: 2023/4/25 19:37
 * @Version 1.0
 **/
@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public void insert(Permission permission) {
        permissionMapper.insert(permission);
    }

    @Override
    public String getMethodUrl(Method method) {
        for (RequestTypeEnum requestTypeEnum : RequestTypeEnum.values()) {
            Annotation annotation = method.getAnnotation(requestTypeEnum.getMappingClass());
            if(Objects.isNull(annotation)){
                continue;
            }
            try {
                Method value = annotation.annotationType().getMethod("value");
                String url[] =  (String[]) value.invoke(annotation);
                return url.length>0?url[0]:"";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    @Transactional
    @Override
    public void deleteAll() {
        permissionMapper.deleteAll();
    }

    @Override
    public PageList<Permission> pageList(PermissionQuery query) {
        Long total = permissionMapper.queryTotal(query);
        if (total <= 0) {
            //说明没查到数据
            return new PageList<>();
        }
        List<Permission> permissions = permissionMapper.queryPageList(query);
        PageList<Permission> pageList = new PageList<>();
        pageList.setRows(permissions);
        pageList.setTotal(total);
        return pageList;
    }

    @Override
    public List<String> getSnsByEmployeeId(Long employeeId) {
        return permissionMapper.querySnsByEmployeeId(employeeId);
    }
}
