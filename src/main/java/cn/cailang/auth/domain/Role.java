package cn.cailang.auth.domain;

import cn.cailang.base.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: Role
 * @Description: 角色
 * @Author: 3299903308@qq.com
 * @Date: 2023/4/27 11:34
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
