package cn.cailang.goods.service;

import cn.cailang.base.service.IBaseService;
import cn.cailang.goods.domain.GoodsType;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cailang
 * @since 2023-05-10
 */
public interface IGoodsTypeService extends IBaseService<GoodsType> {

    /**
     * 获取产品类型数据（一级类型）
     * @return 产品类型数据
     */
    List<GoodsType> selectFirstTypes();
}
