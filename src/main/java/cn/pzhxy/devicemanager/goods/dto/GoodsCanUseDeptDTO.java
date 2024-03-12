package cn.pzhxy.devicemanager.goods.dto;

import cn.pzhxy.devicemanager.goods.dto.GoodsDeptDTO;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: GoodsCanUseDeptDTO
 * @Description: TODO
 * @Author: cailang
 * @Date: 2024/3/12 16:26
 * @Version 1.0
 **/
@Data
public class GoodsCanUseDeptDTO {
    private Long goodsId;
    private List<GoodsDeptDTO> goodsDepts;
}
