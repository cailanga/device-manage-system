package cn.pzhxy.devicemanager.goods.mapper;

import cn.pzhxy.devicemanager.base.mapper.BaseMapper;
import cn.pzhxy.devicemanager.goods.domain.WarehousingGoods;
import cn.pzhxy.devicemanager.goods.query.WarehousingGoodsQuery;

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
