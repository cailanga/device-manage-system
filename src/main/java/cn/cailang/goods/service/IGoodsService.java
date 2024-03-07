package cn.cailang.goods.service;

import cn.cailang.base.service.IBaseService;
import cn.cailang.goods.domain.Goods;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface IGoodsService extends IBaseService<Goods> {

    List<Goods> selectByTypeId(Long id);
}
