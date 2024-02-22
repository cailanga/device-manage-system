package cn.cailang.goods.dto;

import lombok.Data;

/**
 * @ClassName: GoodsHandleDTO
 * @Description: TODO
 * @Author: cailang
 * @Date: 2024/2/8 19:22
 * @Version 1.0
 **/
@Data
public class GoodsHandleDTO {
    private Integer operateTypeId;
    private String description;
    private Long goodsId;
}
