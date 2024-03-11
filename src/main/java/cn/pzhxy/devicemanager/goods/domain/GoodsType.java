package cn.pzhxy.devicemanager.goods.domain;

import cn.pzhxy.devicemanager.base.domain.BaseDomain;
import lombok.Data;


/**
 * <p>
 * 
 * </p>
 *
 * @author lvjiaxin
 * @since 2023-05-10
 */
@Data
public class GoodsType extends BaseDomain{

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String description;
    private Long parentId;
    private GoodsType parent;
}
