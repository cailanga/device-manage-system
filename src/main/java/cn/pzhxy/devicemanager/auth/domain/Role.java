package cn.pzhxy.devicemanager.auth.domain;

import cn.pzhxy.devicemanager.base.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: Role
 * @Description: 角色
 * @Author: 2026972757@qq.com
 * @Date: 2024/2/27 11:34
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseDomain {
    //名称
    private String name;
    //唯一标识
    private String sn;
}
