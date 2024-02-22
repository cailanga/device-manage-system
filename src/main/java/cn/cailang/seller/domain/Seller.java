package cn.cailang.seller.domain;

import cn.cailang.base.domain.BaseDomain;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class Seller extends BaseDomain{

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    private String description;
    /**
     * 图片、视频地址
     */
    private String resource;
    private Long typeId;
    private SellerType type;
}
