package cn.cailang.goods.mapper;

import cn.cailang.base.mapper.BaseMapper;
import cn.cailang.goods.domain.Goods;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */
public interface GoodsMapper extends BaseMapper<Goods> {
    List<Goods> selectByTypeId(Long typeId);
}
