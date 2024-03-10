package cn.cailang.statistics.controller;

import cn.cailang.auth.annotation.JiaXinPermission;
import cn.cailang.base.utils.AjaxResult;
import cn.cailang.base.utils.PageList;
import cn.cailang.statistics.Query.GoodsShowQuery;
import cn.cailang.statistics.service.IGoodsInfoShowService;
import cn.cailang.statistics.vo.GoodsDataChangeVO;
import cn.cailang.statistics.vo.GoodsPriceChangeVO;
import cn.cailang.statistics.vo.GoodsTotalInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: GoodsInfoShowController
 * @Description: 物资数据展示
 * @Author: cailang
 * @Date: 2024/2/28 17:34
 * @Version 1.0
 **/
@JiaXinPermission(name = "物资统计信息查看权限",description = "物资统计信息查看权限")
@Api(value = "物资统计信息",description="物资统计信息相关功能")
@RestController
@RequestMapping("/goodsShow")
public class GoodsInfoShowController {
    @Autowired
    private IGoodsInfoShowService goodsInfoShowService;

    /**
     * 获取物资类型数量统计信息
     * @param query
     * @return
     */
    @ApiOperation("物资类型统计信息查看")
    @JiaXinPermission(name = "物资类型统计信息查看权限",description = "物资类型统计信息查看权限")
    @PostMapping("/countsWithType")
    public AjaxResult getGoodsCountsWithTypeName(@RequestBody GoodsShowQuery query) {
        PageList goodsCountsWithTypeName = goodsInfoShowService.getGoodsCountsWithTypeName(query);
        return AjaxResult.me().setResultObject(goodsCountsWithTypeName);
    }

    /**
     * 获取物资总统计信息
     * @return
     */
    @ApiOperation("物资总统计信息查看")
    @JiaXinPermission(name = "物资总统计信息查看权限",description = "物资总统计信息查看权限")
    @GetMapping("/totalDataInfo")
    public AjaxResult totalDataInfo() {
        GoodsTotalInfoVO goodsTotalInfo = goodsInfoShowService.getTotalDataInfo();
        return AjaxResult.me().setResultObject(goodsTotalInfo);
    }

    /**
     * 获取物资各种情况趋势的信息
     * @return
     */
    @ApiOperation("物资使用情况查看")
    @JiaXinPermission(name = "物资使用情况查看权限",description = "物资使用情况查看权限")
    @PostMapping("/countsWithGoods")
    public AjaxResult countsWithGoods(@RequestBody @Validated GoodsShowQuery query) {
        GoodsDataChangeVO goodsDataChangeVO = goodsInfoShowService.getGoodsDataChangeVO(query);
        return AjaxResult.me().setResultObject(goodsDataChangeVO);
    }

    /**
     * 获取物资价格变化趋势的信息
     * @return
     */
    @ApiOperation("物资价格变化趋势查看")
    @JiaXinPermission(name = "物资价格变化趋势查看权限",description = "物资价格变化趋势查看权限")
    @PostMapping("/goodsPriceChange/{goodsId}")
    public AjaxResult goodsPriceChange(@PathVariable(value = "goodsId") Long goodsId) {
        List<GoodsPriceChangeVO> goodsPriceChangeVOList = goodsInfoShowService.getGoodsPriceChangeVO(goodsId);
        return AjaxResult.me().setResultObject(goodsPriceChangeVOList);
    }

    /**
     * 获取物资信息
     * @return
     */
    @ApiOperation("近期物资信息查看")
    @JiaXinPermission(name = "近期物资信息查看权限",description = "近期物资信息查看权限")
    @PostMapping("/goods")
    public AjaxResult goodsInfo(@RequestBody GoodsShowQuery goodsShowQuery) {
        PageList<List<String>> goodss = goodsInfoShowService.getGoodsInfo(goodsShowQuery);
        return AjaxResult.me().setResultObject(goodss);
    }

}
