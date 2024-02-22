package cn.cailang.seller.service.impl;

import cn.cailang.base.service.impl.BaseServiceImpl;
import cn.cailang.seller.domain.SellerType;
import cn.cailang.seller.mapper.SellerTypeMapper;
import cn.cailang.seller.service.ISellerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cailang
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
