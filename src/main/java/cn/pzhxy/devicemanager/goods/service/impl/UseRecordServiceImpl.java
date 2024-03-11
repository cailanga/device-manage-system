package cn.pzhxy.devicemanager.goods.service.impl;

import cn.pzhxy.devicemanager.base.service.impl.BaseServiceImpl;
import cn.pzhxy.devicemanager.base.utils.PageList;
import cn.pzhxy.devicemanager.device.domain.Devices;
import cn.pzhxy.devicemanager.device.mapper.DevicesMapper;
import cn.pzhxy.devicemanager.goods.domain.Goods;
import cn.pzhxy.devicemanager.goods.domain.UseRecord;
import cn.pzhxy.devicemanager.goods.dto.UseRecordPageInfoDTO;
import cn.pzhxy.devicemanager.goods.mapper.GoodsMapper;
import cn.pzhxy.devicemanager.goods.mapper.UseRecordMapper;
import cn.pzhxy.devicemanager.goods.service.IUseRecordService;
import cn.pzhxy.devicemanager.org.domain.Department;
import cn.pzhxy.devicemanager.org.domain.Employee;
import cn.pzhxy.devicemanager.org.mapper.DepartmentMapper;
import cn.pzhxy.devicemanager.org.mapper.EmployeeMapper;
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
public class UseRecordServiceImpl extends BaseServiceImpl<UseRecord> implements IUseRecordService {
    @Autowired
    private UseRecordMapper useRecordMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private DevicesMapper devicesMapper;
    @Override
    public PageList selectAllForOperator(UseRecordPageInfoDTO dto) {
        List<UseRecord> useRecords = useRecordMapper.selectAllForOperator(dto);
        Long total = useRecordMapper.selectCountForOperator(dto);
        for (UseRecord useRecord : useRecords) {
            Employee operator = useRecord.getOperator();
            operator = employeeMapper.selectById(useRecord.getOperatorId());
            useRecord.setOperator(operator);

            Employee user = useRecord.getUser();
            user = employeeMapper.selectById(useRecord.getUseId());
            useRecord.setUser(user);

            String[] split = useRecord.getDeptIdPath().split("/");
            String name = "";
            for (String s : split) {
                if (!"".equals(s)) {
                    Department department = departmentMapper.selectById(s);
                    name = name+"-"+department.getName();
                }
            }
            useRecord.setDeptName(name.substring(1, name.length()));
            if (useRecord.getType()==1){
                //物资
                Goods goods = goodsMapper.selectById(useRecord.getGoodsId());
                useRecord.setGoods(goods);
            }else {
                Devices devices = devicesMapper.selectById(useRecord.getGoodsId());
                useRecord.setDevices(devices);
            }
        }
        return new PageList<>(total,useRecords);
    }
}
