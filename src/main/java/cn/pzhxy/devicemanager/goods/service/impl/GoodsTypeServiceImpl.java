package cn.pzhxy.devicemanager.goods.service.impl;

import cn.pzhxy.devicemanager.base.service.impl.BaseServiceImpl;
import cn.pzhxy.devicemanager.goods.domain.GoodsType;
import cn.pzhxy.devicemanager.goods.mapper.GoodsTypeMapper;
import cn.pzhxy.devicemanager.goods.service.IGoodsTypeService;
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
public class GoodsTypeServiceImpl extends BaseServiceImpl<GoodsType> implements IGoodsTypeService {
    @Autowired
    private GoodsTypeMapper goodsTypeMapper;
    @Override
    public List<GoodsType> selectFirstTypes() {
        return goodsTypeMapper.selectFirstTypes();
    }
}
