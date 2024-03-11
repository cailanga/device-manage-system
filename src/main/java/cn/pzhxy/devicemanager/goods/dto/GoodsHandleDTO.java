package cn.pzhxy.devicemanager.goods.dto;

import lombok.Data;

/**
 * @ClassName: GoodsHandleDTO
 * @Description: TODO
 * @Author: lvjiaxin
 * @Date: 2024/2/8 19:22
 * @Version 1.0
 **/
@Data
public class GoodsHandleDTO {
    private Integer operateTypeId;
    private String description;
    private Long goodsId;
}
