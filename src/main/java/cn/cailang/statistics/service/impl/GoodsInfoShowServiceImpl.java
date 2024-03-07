package cn.cailang.statistics.service.impl;

import cn.cailang.base.utils.PageList;
import cn.cailang.goods.domain.Goods;
import cn.cailang.statistics.Query.GoodsShowQuery;
import cn.cailang.statistics.mapper.GoodsShowMapper;
import cn.cailang.statistics.service.IGoodsInfoShowService;
import cn.cailang.statistics.vo.GoodsDataChangeStatisticVO;
import cn.cailang.statistics.vo.GoodsDataChangeVO;
import cn.cailang.statistics.vo.GoodsPriceChangeVO;
import cn.cailang.statistics.vo.GoodsTotalInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: GoodsInfoShowServiceImpl
 * @Description: TODO
 * @Author: cailang
 * @Date: 2024/2/28 17:36
 * @Version 1.0
 **/
@Service
public class GoodsInfoShowServiceImpl implements IGoodsInfoShowService {
    @Autowired
    private GoodsShowMapper goodsShowMapper;
    @Override
    public PageList getGoodsCountsWithTypeName(GoodsShowQuery query) {
        Long goodsTypeTotal = goodsShowMapper.getGoodsTypeTotal(query);
        Long total = 0L;
        if (goodsTypeTotal!=null) {
            total = goodsTypeTotal;
        }
        List<Map<String, Object>> goodsTypeUseCountsWithTypeName = goodsShowMapper.getGoodsTypeUseCountsWithTypeName(query);
        List<Map<String, Object>> goodsTypeCountsWithTypeName = goodsShowMapper.getGoodsTypeCountsWithTypeName(query);
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(goodsTypeCountsWithTypeName);
        arrayList.add(goodsTypeUseCountsWithTypeName);
        return new PageList<>(total,arrayList);
    }

    @Override
    public GoodsTotalInfoVO getTotalDataInfo() {
        //物资总数量
        Long total = goodsShowMapper.selectGoodsTotal();
        //已入库物资数量
        Long inNum = goodsShowMapper.selectGoodsInTotal();
        //未入库物资数量
        Long notInNum = goodsShowMapper.selectGoodsNotInTotal();

        //可用物资数量
        Long canUseTotal = goodsShowMapper.selectGoodsCanUseTotal();
        GoodsTotalInfoVO goodsTotalInfoVO = new GoodsTotalInfoVO();
        goodsTotalInfoVO.setTotalNum(total);
        goodsTotalInfoVO.setInNum(inNum);
        goodsTotalInfoVO.setNoInNum(notInNum);
        goodsTotalInfoVO.setCanUseNum(canUseTotal);
        return goodsTotalInfoVO;
    }

    @Override
    public GoodsDataChangeVO getGoodsDataChangeVO(GoodsShowQuery query) {
        Integer year = query.getYear();
        Integer month = query.getMonth();
        String type = query.getType();
        GoodsDataChangeVO goodsDataChangeVO = new GoodsDataChangeVO();
        ArrayList<List<Long>> data = new ArrayList<>();
        //查询入库物资数量
        List<GoodsDataChangeStatisticVO> statistics = null;
        //查询物资使用量
        List<GoodsDataChangeStatisticVO> usageStatistics = null;
        if ("year".equals(type)){
            //按年查询
            //查询入库物资数量
            statistics = goodsShowMapper.getYearlyStatistics(year);
            //查询物资使用量
            usageStatistics = goodsShowMapper.getYearlyUsageStatistics(year);

        } else if ("month".equals(type)) {
            //按月份查询
            //查询入库物资数量
            statistics = goodsShowMapper.getMonthlyStatistics(year);
            //查询物资使用量
            usageStatistics = goodsShowMapper.getMonthlyUsageStatistics(year);
        } else if ("quarter".equals(type)) {
            //按季度查询
            //查询入库物资数量
            statistics = goodsShowMapper.getQuarterlyStatistics(year);
            //查询物资使用量
            usageStatistics = goodsShowMapper.getQuarterlyUsageStatistics(year);
        }else if("day".equals(type)){
            //查询入库物资数量
            statistics = goodsShowMapper.getDailyStatistics(String.format("%04d", year), String.format("%02d", month));
            //查询物资使用量
            usageStatistics = goodsShowMapper.getDailyUsageStatistics(String.format("%04d", year), String.format("%02d", month));
        }
        assert statistics != null;
        goodsDataChangeVO.setTimeArray(statistics.stream().map(GoodsDataChangeStatisticVO::getDimension).collect(Collectors.toList()));
        data.add(statistics.stream().map(item->{
            if (item.getTotalCount() != null) {
                return item.getTotalCount();
            }else {
                return 0L;
            }
        }).collect(Collectors.toList()));
        assert usageStatistics != null;
        data.add(usageStatistics.stream().map(item->{
            if (item.getTotalCount() != null) {
                return item.getTotalCount();
            }else {
                return 0L;
            }
        }).collect(Collectors.toList()));
        goodsDataChangeVO.setData(data);
        return goodsDataChangeVO;
    }

    @Override
    public List<GoodsPriceChangeVO> getGoodsPriceChangeVO(Long goodsId) {
        return goodsShowMapper.getGoodsPriceChangeVO(goodsId);
    }

    @Override
    public PageList<List<String>> getGoodsInfo(GoodsShowQuery goodsShowQuery) {
        Long total = goodsShowMapper.queryTotal(goodsShowQuery);
        List<Goods> goodss = goodsShowMapper.queryPageList(goodsShowQuery);
        List<List<String>> data = new ArrayList<>();
        goodss.forEach(goods -> {
            if (goods!=null){
                ArrayList<String> strings = new ArrayList<>();
                strings.add(goods.getTypeId()+"");
                strings.add(goods.getName());
                strings.add(goods.getPrice()+"");
                SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                // 使用format方法将Date对象格式化成字符串
                String formattedDate = dateTimeFormat.format(goods.getCreateTime());
                strings.add(formattedDate);
                data.add(strings);
            }
        });
        return new PageList<>(total, data);
    }
}
