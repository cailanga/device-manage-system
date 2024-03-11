package cn.pzhxy.devicemanager.goods.service;

import cn.pzhxy.devicemanager.base.service.IBaseService;
import cn.pzhxy.devicemanager.base.utils.PageList;
import cn.pzhxy.devicemanager.goods.domain.WarehousingGoods;
import cn.pzhxy.devicemanager.goods.dto.GoodsHandleDTO;
import cn.pzhxy.devicemanager.goods.query.WarehousingGoodsQuery;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface IWarehousingGoodsService extends IBaseService<WarehousingGoods> {

    void batchWarehousing(List<Long> ids, HttpServletRequest request);

    void handle(GoodsHandleDTO dto, HttpServletRequest request);

    PageList<WarehousingGoods> pageListForChecking(WarehousingGoodsQuery query);
}
