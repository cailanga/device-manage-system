package cn.cailang.auth.listener;

import cn.cailang.auth.annotation.RonghuaPermission;
import cn.cailang.auth.domain.Permission;
import cn.cailang.auth.mapper.PermissionMapper;
import cn.cailang.auth.service.IPermissionService;
import cn.cailang.base.utils.ClassUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName: InitPermissionListener
 * @Description: 在项目启动时扫描controller包
 * @Author: 3299903308@qq.com
 * @Date: 2023/4/25 19:14
 * @Version 1.0
 **/
//@WebListener
//@Component
public class InitPermissionListener implements ServletContextListener {
    @Value("${auth.permission.controller-package}")
    private String controllerPackage;
    @Autowired
    private IPermissionService permissionService;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //扫描controller包
        List<Class> classList = ClassUtils.getAllClassName(controllerPackage);
        //项目启动先删除数据
        permissionService.deleteAll();

        classList.forEach(aClass -> {
            //判断当前类是否有@RonghuaPermission注解
            RonghuaPermission ronghuaPermission = (RonghuaPermission) aClass.getAnnotation(RonghuaPermission.class);
            if (Objects.isNull(ronghuaPermission)) {
                //如果没有此注解，则跳过，不需要权限
                return;
            }
            String name = ronghuaPermission.name();
            String description = ronghuaPermission.description();
            RequestMapping requestMapping = (RequestMapping) aClass.getAnnotation(RequestMapping.class);
            //类上的url
            String url = requestMapping.value()[0];
            //唯一标识
            String simpleName = aClass.getSimpleName();
            //封装权限对象
            Permission permission = new Permission();
            permission.setSn(simpleName);
            permission.setName(name);
            permission.setDescs(description);
            permission.setUrl(url);

            permissionService.insert(permission);
            //获取该类上的所有方法
            Method[] methods = aClass.getMethods();

            for (Method method : methods) {
                RonghuaPermission methodAnnotation = method.getAnnotation(RonghuaPermission.class);
                if (Objects.isNull(methodAnnotation)) {
                    //如果没有@RonghuaPermission注解，则不需要权限
                    continue;
                }
                String methName = methodAnnotation.name();
                String methDescription = methodAnnotation.description();
                //获取方法的url
                String methUrl = permissionService.getMethodUrl(method);
                //获取唯一标识
                String methSn = aClass.getSimpleName()+":"+ method.getName();

                //获取父权限的id
                Long parent_id = permission.getId();

                //封装permission对象
                Permission methPermission = new Permission();
                methPermission.setName(methName);
                methPermission.setDescs(methDescription);
                methPermission.setSn(methSn);
                methPermission.setUrl(url+methUrl);
                methPermission.setParentId(parent_id);

                permissionService.insert(methPermission);

            }


        });
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
