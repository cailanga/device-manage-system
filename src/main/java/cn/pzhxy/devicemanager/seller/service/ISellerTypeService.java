package cn.pzhxy.devicemanager.seller.service;

import cn.pzhxy.devicemanager.base.service.IBaseService;
import cn.pzhxy.devicemanager.seller.domain.SellerType;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lvjiaxin
 * @since 2023-05-10
 */
public interface ISellerTypeService extends IBaseService<SellerType> {

    /**
     * 获取产品类型数据（一级类型）
     * @return 产品类型数据
     */
    List<SellerType> selectFirstTypes();
}
