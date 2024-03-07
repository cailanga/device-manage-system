package cn.cailang.goods.service.impl;

import cn.cailang.base.service.impl.BaseServiceImpl;
import cn.cailang.goods.domain.Goods;
import cn.cailang.goods.mapper.GoodsMapper;
import cn.cailang.goods.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 */
@Service
public class GoodsServiceImpl extends BaseServiceImpl<Goods> implements IGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public List<Goods> selectByTypeId(Long id) {
        return goodsMapper.selectByTypeId(id);
    }
}
