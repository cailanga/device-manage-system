package cn.pzhxy.devicemanager.seller.mapper;

import cn.pzhxy.devicemanager.base.mapper.BaseMapper;
import cn.pzhxy.devicemanager.seller.domain.SellerType;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lvjiaxin
 * @since 2023-05-10
 */
public interface SellerTypeMapper extends BaseMapper<SellerType> {
    /**
     * 获取产品类型数据（一级类型）
     * @return 产品类型数据
     */
    List<SellerType> selectFirstTypes();
}
