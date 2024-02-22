package cn.cailang.goods.service;

import cn.cailang.base.service.IBaseService;
import cn.cailang.base.utils.PageList;
import cn.cailang.goods.domain.Goods;
import cn.cailang.goods.domain.WarehousingGoods;
import cn.cailang.goods.dto.GoodsHandleDTO;
import cn.cailang.goods.query.WarehousingGoodsQuery;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface IWarehousingGoodsService extends IBaseService<WarehousingGoods> {

    void batchWarehousing(List<Long> ids, HttpServletRequest request);

    void handle(GoodsHandleDTO dto,HttpServletRequest request);

    PageList<WarehousingGoods> pageListForChecking(WarehousingGoodsQuery query);
}
