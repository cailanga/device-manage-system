package cn.pzhxy.devicemanager.device.service.impl;

import cn.pzhxy.devicemanager.base.service.impl.BaseServiceImpl;
import cn.pzhxy.devicemanager.base.utils.LoginUtil;
import cn.pzhxy.devicemanager.base.utils.PageList;
import cn.pzhxy.devicemanager.device.domain.DevicesOperaterLog;
import cn.pzhxy.devicemanager.device.domain.Devices;
import cn.pzhxy.devicemanager.device.domain.WarehousingDevices;
import cn.pzhxy.devicemanager.device.dto.DevicesHandleDTO;
import cn.pzhxy.devicemanager.device.mapper.DevicesMapper;
import cn.pzhxy.devicemanager.device.mapper.DevicesOperaterLogMapper;
import cn.pzhxy.devicemanager.device.mapper.WarehousingDevicesMapper;
import cn.pzhxy.devicemanager.device.query.WarehousingDevicesQuery;
import cn.pzhxy.devicemanager.device.service.IWarehousingDevicesService;
import cn.pzhxy.devicemanager.org.domain.Employee;
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
public class WarehousingDevicesServiceImpl extends BaseServiceImpl<WarehousingDevices> implements IWarehousingDevicesService {

    @Autowired
    private WarehousingDevicesMapper warehousingGoodsMapper;
    @Autowired
    private DevicesOperaterLogMapper goodsOperaterLogMapper;
    @Autowired
    private DevicesMapper goodsMapper;
    @Override
    public void batchWarehousing(List<Long> ids, HttpServletRequest request) {
        Employee userInfo = LoginUtil.getUserInfo(request);
        warehousingGoodsMapper.batchWarehousing(ids);
        for (Long id : ids) {
            WarehousingDevices warehousingGoods = warehousingGoodsMapper.selectById(id);
            String name = warehousingGoods.getName();
            DevicesOperaterLog goodsOperaterLog = new DevicesOperaterLog();
            goodsOperaterLog.setOperatorId(userInfo.getId());
            goodsOperaterLog.setUpdateTime(new Date());
            goodsOperaterLog.setOperatorName(userInfo.getUsername());
            goodsOperaterLog.setDescription("批量入库设备，设备id为"+id);
            goodsOperaterLog.setType("入库");
            goodsOperaterLog.setDeviceId(id);
            goodsOperaterLog.setDeviceName(name);
            goodsOperaterLogMapper.insert(goodsOperaterLog);
        }

    }

    @Override
    public void handle(DevicesHandleDTO dto, HttpServletRequest request) {
        Long goodsId = dto.getDeviceId();
        String description = dto.getDescription();
        Employee userInfo = LoginUtil.getUserInfo(request);
        DevicesOperaterLog goodsOperaterLog = new DevicesOperaterLog();
        goodsOperaterLog.setType("审批");
        goodsOperaterLog.setOperatorId(userInfo.getId());
//        goodsOperaterLog.setDescription(description);
        goodsOperaterLog.setOperatorName(userInfo.getUsername());
        goodsOperaterLog.setDeviceId(dto.getDeviceId());
        WarehousingDevices warehousingGoods = warehousingGoodsMapper.selectById(goodsId);
        goodsOperaterLog.setDeviceName(warehousingGoods.getName());
        goodsOperaterLog.setUpdateTime(new Date());
        if (dto.getOperateTypeId()==1){
            //审批通过,入库成功
            warehousingGoods.setStatus(2);
            Devices goods = new Devices();
            BeanUtils.copyProperties(warehousingGoods,goods);
            goods.setUseCount(0);
            goods.setCreateTime(new Date());
            goodsMapper.insert(goods);
            goodsOperaterLog.setDescription("审批通过："+description);
        }else if (dto.getOperateTypeId()==2){
            //驳回
            warehousingGoods.setStatus(-1);
            goodsOperaterLog.setDescription("审批驳回："+description);
        }
        warehousingGoodsMapper.update(warehousingGoods);
        goodsOperaterLogMapper.insert(goodsOperaterLog);
    }

    @Override
    public PageList<WarehousingDevices> pageListForChecking(WarehousingDevicesQuery query) {
        Long total = warehousingGoodsMapper.queryTotalForChecking(query);
        if(total>0){
            List<WarehousingDevices> rows = warehousingGoodsMapper.queryPageListForChecking(query);
            return new PageList<>(total,rows);
        }
        return new PageList<>();
    }
}
