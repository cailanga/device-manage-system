package cn.pzhxy.devicemanager.goods.service;

import cn.pzhxy.devicemanager.base.service.IBaseService;
import cn.pzhxy.devicemanager.base.utils.PageList;
import cn.pzhxy.devicemanager.goods.domain.Goods;
import cn.pzhxy.devicemanager.goods.dto.GoodsCanUseDeptDTO;
import cn.pzhxy.devicemanager.goods.dto.GoodsDeptDTO;
import cn.pzhxy.devicemanager.goods.query.GoodsQuery;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface IGoodsService extends IBaseService<Goods> {

    List<Goods> selectByTypeId(Long id);

    void setCanUseDept(GoodsCanUseDeptDTO dto, HttpServletRequest request);

    PageList<Goods> pageListByDept(GoodsQuery query, HttpServletRequest request);

    List<GoodsDeptDTO> selectDeptIdsByGoodsId(Long deviceId);
}
