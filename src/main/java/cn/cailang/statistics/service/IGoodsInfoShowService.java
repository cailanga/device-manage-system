package cn.cailang.statistics.service;

import cn.cailang.base.utils.PageList;
import cn.cailang.statistics.Query.GoodsShowQuery;
import cn.cailang.statistics.vo.GoodsDataChangeVO;
import cn.cailang.statistics.vo.GoodsPriceChangeVO;
import cn.cailang.statistics.vo.GoodsTotalInfoVO;

import java.util.List;

public interface IGoodsInfoShowService {
    PageList getGoodsCountsWithTypeName(GoodsShowQuery query);

    GoodsTotalInfoVO getTotalDataInfo();

    GoodsDataChangeVO getGoodsDataChangeVO(GoodsShowQuery query);

    List<GoodsPriceChangeVO> getGoodsPriceChangeVO(Long GoodsId);

    PageList<List<String>> getGoodsInfo(GoodsShowQuery GoodsShowQuery);
}
