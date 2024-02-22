package cn.cailang.goods.domain;

import cn.cailang.base.domain.BaseDomain;
import cn.cailang.seller.domain.Seller;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName: WarehousingGoods
 * @Description: 未入库物资
 * @Author: cailang
 * @Date: 2024/2/8 16:17
 * @Version 1.0
 **/
@Data
public class WarehousingGoods extends BaseDomain {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private BigDecimal price;
    private Integer count;
    private Integer status;
    private String description;
    /**
     * 图片、视频地址
     */
    private String resource;
    private Long typeId;
    private GoodsType type;
    private Long sellerId;
    private Seller seller;
}
