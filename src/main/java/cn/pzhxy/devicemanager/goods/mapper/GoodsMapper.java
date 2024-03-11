package cn.pzhxy.devicemanager.goods.mapper;

import cn.pzhxy.devicemanager.base.mapper.BaseMapper;
import cn.pzhxy.devicemanager.goods.domain.Goods;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */
public interface GoodsMapper extends BaseMapper<Goods> {
    List<Goods> selectByTypeId(Long typeId);
}
