package cn.cailang.goods.service.impl;

import cn.cailang.base.service.impl.BaseServiceImpl;
import cn.cailang.goods.domain.GoodsType;
import cn.cailang.goods.mapper.GoodsTypeMapper;
import cn.cailang.goods.service.IGoodsTypeService;
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
public class GoodsTypeServiceImpl extends BaseServiceImpl<GoodsType> implements IGoodsTypeService {
    @Autowired
    private GoodsTypeMapper goodsTypeMapper;
    @Override
    public List<GoodsType> selectFirstTypes() {
        return goodsTypeMapper.selectFirstTypes();
    }
}
