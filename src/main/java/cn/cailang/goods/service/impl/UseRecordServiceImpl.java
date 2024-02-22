package cn.cailang.goods.service.impl;

import cn.cailang.base.service.impl.BaseServiceImpl;
import cn.cailang.base.utils.PageList;
import cn.cailang.device.domain.Devices;
import cn.cailang.device.mapper.DevicesMapper;
import cn.cailang.goods.domain.Goods;
import cn.cailang.goods.domain.UseRecord;
import cn.cailang.goods.dto.UseRecordPageInfoDTO;
import cn.cailang.goods.mapper.GoodsMapper;
import cn.cailang.goods.mapper.UseRecordMapper;
import cn.cailang.goods.service.IGoodsService;
import cn.cailang.goods.service.IUseRecordService;
import cn.cailang.org.domain.Department;
import cn.cailang.org.domain.Employee;
import cn.cailang.org.mapper.DepartmentMapper;
import cn.cailang.org.mapper.EmployeeMapper;
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
