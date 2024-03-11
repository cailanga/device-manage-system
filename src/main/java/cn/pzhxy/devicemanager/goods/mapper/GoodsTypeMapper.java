package cn.pzhxy.devicemanager.goods.mapper;

import cn.pzhxy.devicemanager.base.mapper.BaseMapper;
import cn.pzhxy.devicemanager.goods.domain.GoodsType;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lvjiaxin
 * @since 2023-05-10
 */
public interface GoodsTypeMapper extends BaseMapper<GoodsType> {
    /**
     * 获取产品类型数据（一级类型）
     * @return 产品类型数据
     */
    List<GoodsType> selectFirstTypes();
}
