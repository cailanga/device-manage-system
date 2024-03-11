package cn.pzhxy.devicemanager.goods.service;

import cn.pzhxy.devicemanager.base.service.IBaseService;
import cn.pzhxy.devicemanager.goods.domain.Goods;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface IGoodsService extends IBaseService<Goods> {

    List<Goods> selectByTypeId(Long id);
}
