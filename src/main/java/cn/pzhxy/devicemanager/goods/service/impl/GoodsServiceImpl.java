package cn.pzhxy.devicemanager.goods.service.impl;

import cn.pzhxy.devicemanager.base.service.impl.BaseServiceImpl;
import cn.pzhxy.devicemanager.base.utils.LoginUtil;
import cn.pzhxy.devicemanager.base.utils.PageList;
import cn.pzhxy.devicemanager.goods.domain.Goods;
import cn.pzhxy.devicemanager.goods.dto.GoodsCanUseDeptDTO;
import cn.pzhxy.devicemanager.goods.dto.GoodsDeptDTO;
import cn.pzhxy.devicemanager.goods.mapper.GoodsDepartmentMapper;
import cn.pzhxy.devicemanager.goods.query.GoodsQuery;
import cn.pzhxy.devicemanager.goods.domain.Goods;
import cn.pzhxy.devicemanager.goods.mapper.GoodsMapper;
import cn.pzhxy.devicemanager.goods.service.IGoodsService;
import cn.pzhxy.devicemanager.org.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private GoodsDepartmentMapper goodsDepartmentMapper;

    @Override
    public List<Goods> selectByTypeId(Long id) {
        return goodsMapper.selectByTypeId(id);
    }

    @Override
    public void setCanUseDept(GoodsCanUseDeptDTO dto, HttpServletRequest request) {
        Employee userInfo = LoginUtil.getUserInfo(request);
        Long goodsId = dto.getGoodsId();
        List<GoodsDeptDTO> goodsDepts = dto.getGoodsDepts();
        //先删除表中的设备部门对应关系
        goodsDepartmentMapper.deleteByGoodsId(goodsId);
        if (goodsDepts.size()>0){
            //要添加新增的数据
            goodsDepts.forEach(goodsDeptDTO -> {
                goodsDeptDTO.setGoodsId(goodsId);
                goodsDepartmentMapper.insert(goodsDeptDTO);
            });
        }
    }

    @Override
    public PageList<Goods> pageListByDept(GoodsQuery query, HttpServletRequest request) {
        Employee userInfo = LoginUtil.getUserInfo(request);
        List<Long> goodsIds = goodsDepartmentMapper.selectGoodsIdByDepartmentId(query,userInfo.getDepartmentId());
        if (goodsIds.size()==0){
            return new PageList<>();
        }
        List<Goods> goodss = goodsDepartmentMapper.selectGoodsByDepartmentId(query, userInfo.getDepartmentId());
        return new PageList<>(goodsIds.size()+0L,goodss);
    }

    @Override
    public List<GoodsDeptDTO> selectDeptIdsByGoodsId(Long goodsId) {
        return goodsDepartmentMapper.selectDeptIdsByGoodsId(goodsId);
    }
}
