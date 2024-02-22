package cn.cailang.device.domain;

import cn.cailang.base.domain.BaseDomain;
import lombok.Data;


/**
 * <p>
 * 
 * </p>
 *
 * @author cailang
 * @since 2023-05-10
 */
@Data
public class DevicesType extends BaseDomain{

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String description;
    private Long parentId;
    private DevicesType parent;
}
