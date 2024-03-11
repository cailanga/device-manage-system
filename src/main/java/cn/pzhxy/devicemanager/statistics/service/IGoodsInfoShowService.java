package cn.pzhxy.devicemanager.statistics.service;

import cn.pzhxy.devicemanager.base.utils.PageList;
import cn.pzhxy.devicemanager.statistics.Query.GoodsShowQuery;
import cn.pzhxy.devicemanager.statistics.vo.GoodsDataChangeVO;
import cn.pzhxy.devicemanager.statistics.vo.GoodsPriceChangeVO;
import cn.pzhxy.devicemanager.statistics.vo.GoodsTotalInfoVO;

import java.util.List;

public interface IGoodsInfoShowService {
    PageList getGoodsCountsWithTypeName(GoodsShowQuery query);

    GoodsTotalInfoVO getTotalDataInfo();

    GoodsDataChangeVO getGoodsDataChangeVO(GoodsShowQuery query);

    List<GoodsPriceChangeVO> getGoodsPriceChangeVO(Long GoodsId);

    PageList<List<String>> getGoodsInfo(GoodsShowQuery GoodsShowQuery);
}
