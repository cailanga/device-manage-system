package cn.cailang.goods.mapper;

import cn.cailang.base.mapper.BaseMapper;
import cn.cailang.goods.domain.GoodsType;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cailang
 * @since 2023-05-10
 */
public interface GoodsTypeMapper extends BaseMapper<GoodsType> {
    /**
     * 获取产品类型数据（一级类型）
     * @return 产品类型数据
     */
    List<GoodsType> selectFirstTypes();
}
