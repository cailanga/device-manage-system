package cn.cailang.device.domain;

import cn.cailang.base.domain.BaseDomain;
import cn.cailang.seller.domain.Seller;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
public class Devices extends BaseDomain{

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private Integer count;
    private Integer useCount;
    private BigDecimal price;
    private String description;
    /**
     * 图片、视频地址
     */
    private String resource;
    private Long typeId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateofmanufacture;
    private Integer qualityguaranteeperiod;
    private DevicesType type;
    private Long sellerId;
    private Seller seller;

    private Date createTime;
}
