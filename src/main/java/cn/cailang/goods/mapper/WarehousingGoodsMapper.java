package cn.cailang.goods.mapper;

import cn.cailang.base.mapper.BaseMapper;
import cn.cailang.goods.domain.Goods;
import cn.cailang.goods.domain.WarehousingGoods;
import cn.cailang.goods.query.WarehousingGoodsQuery;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */
public interface WarehousingGoodsMapper extends BaseMapper<WarehousingGoods> {
 //批量入库
    void batchWarehousing(List<Long> ids);

    Long queryTotalForChecking(WarehousingGoodsQuery query);

    List<WarehousingGoods> queryPageListForChecking(WarehousingGoodsQuery query);
}
