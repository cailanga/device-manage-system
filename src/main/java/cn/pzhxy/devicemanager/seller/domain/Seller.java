package cn.pzhxy.devicemanager.seller.domain;

import cn.pzhxy.devicemanager.base.domain.BaseDomain;
import lombok.Data;


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
