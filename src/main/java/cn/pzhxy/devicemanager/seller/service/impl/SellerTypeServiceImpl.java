package cn.pzhxy.devicemanager.seller.service.impl;

import cn.pzhxy.devicemanager.base.service.impl.BaseServiceImpl;
import cn.pzhxy.devicemanager.seller.domain.SellerType;
import cn.pzhxy.devicemanager.seller.mapper.SellerTypeMapper;
import cn.pzhxy.devicemanager.seller.service.ISellerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lvjiaxin
 * @since 2023-05-10
 */
@Service
public class SellerTypeServiceImpl extends BaseServiceImpl<SellerType> implements ISellerTypeService {
    @Autowired
    private SellerTypeMapper goodsTypeMapper;
    @Override
    public List<SellerType> selectFirstTypes() {
        return goodsTypeMapper.selectFirstTypes();
    }
}
