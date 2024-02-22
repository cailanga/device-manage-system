package cn.cailang.goods.service.impl;

import cn.cailang.base.service.impl.BaseServiceImpl;
import cn.cailang.base.utils.LoginUtil;
import cn.cailang.base.utils.PageList;
import cn.cailang.goods.domain.Goods;
import cn.cailang.goods.domain.GoodsOperaterLog;
import cn.cailang.goods.domain.WarehousingGoods;
import cn.cailang.goods.dto.GoodsHandleDTO;
import cn.cailang.goods.mapper.GoodsMapper;
import cn.cailang.goods.mapper.GoodsOperaterLogMapper;
import cn.cailang.goods.mapper.WarehousingGoodsMapper;
import cn.cailang.goods.query.WarehousingGoodsQuery;
import cn.cailang.goods.service.IGoodsService;
import cn.cailang.goods.service.IWarehousingGoodsService;
import cn.cailang.org.domain.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 */
@Service
public class WarehousingGoodsServiceImpl extends BaseServiceImpl<WarehousingGoods> implements IWarehousingGoodsService {

    @Autowired
    private WarehousingGoodsMapper warehousingGoodsMapper;
    @Autowired
    private GoodsOperaterLogMapper goodsOperaterLogMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public void batchWarehousing(List<Long> ids, HttpServletRequest request) {
        Employee userInfo = LoginUtil.getUserInfo(request);
        warehousingGoodsMapper.batchWarehousing(ids);
        for (Long id : ids) {
            WarehousingGoods warehousingGoods = warehousingGoodsMapper.selectById(id);
            String name = warehousingGoods.getName();
            GoodsOperaterLog goodsOperaterLog = new GoodsOperaterLog();
            goodsOperaterLog.setOperatorId(userInfo.getId());
            goodsOperaterLog.setUpdateTime(new Date());
            goodsOperaterLog.setOperatorName(userInfo.getUsername());
            goodsOperaterLog.setDescription("批量入库物资，物资id为"+id);
            goodsOperaterLog.setType("入库");
            goodsOperaterLog.setGoodsId(id);
            goodsOperaterLog.setGoodsName(name);
            goodsOperaterLogMapper.insert(goodsOperaterLog);
        }

    }

    @Override
    public void handle(GoodsHandleDTO dto,HttpServletRequest request) {
        Long goodsId = dto.getGoodsId();
        String description = dto.getDescription();
        Employee userInfo = LoginUtil.getUserInfo(request);
        GoodsOperaterLog goodsOperaterLog = new GoodsOperaterLog();
        goodsOperaterLog.setType("审批");
        goodsOperaterLog.setOperatorId(userInfo.getId());
        goodsOperaterLog.setDescription(description);
        goodsOperaterLog.setOperatorName(userInfo.getUsername());
        goodsOperaterLog.setGoodsId(dto.getGoodsId());
        WarehousingGoods warehousingGoods = warehousingGoodsMapper.selectById(goodsId);
        goodsOperaterLog.setGoodsName(warehousingGoods.getName());
        goodsOperaterLog.setUpdateTime(new Date());
        if (dto.getOperateTypeId()==1){
            //审批通过,入库成功
            warehousingGoods.setStatus(2);
            Goods goods = new Goods();
            BeanUtils.copyProperties(warehousingGoods,goods);
            goods.setUseCount(0);
            goodsMapper.insert(goods);
        }else if (dto.getOperateTypeId()==2){
            //驳回
            warehousingGoods.setStatus(-1);
        }
        warehousingGoodsMapper.update(warehousingGoods);
        goodsOperaterLogMapper.insert(goodsOperaterLog);
    }

    @Override
    public PageList<WarehousingGoods> pageListForChecking(WarehousingGoodsQuery query) {
        Long total = warehousingGoodsMapper.queryTotalForChecking(query);
        if(total>0){
            List<WarehousingGoods> rows = warehousingGoodsMapper.queryPageListForChecking(query);
            return new PageList<>(total,rows);
        }
        return new PageList<>();
    }
}
