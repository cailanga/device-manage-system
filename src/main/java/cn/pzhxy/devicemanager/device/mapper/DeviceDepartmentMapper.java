package cn.pzhxy.devicemanager.device.mapper;

import cn.pzhxy.devicemanager.device.domain.Devices;
import cn.pzhxy.devicemanager.device.dto.DeviceDeptDTO;
import cn.pzhxy.devicemanager.device.query.DevicesQuery;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName: DeviceDepartmentMapper
 * @Description: TODO
 * @Author: cailang
 * @Date: 2024/3/12 16:45
 * @Version 1.0
 **/
@Mapper
public interface DeviceDepartmentMapper {
    @Delete("delete from t_device_department where deviceId=#{deviceId}")
    public void deleteByDeviceId(Long deviceId);
    @Insert("insert into t_device_department(deviceId, deptId) values(#{deviceId}, #{deptId})")
    void insert(DeviceDeptDTO deviceDeptDTO);

    @Select("<script>" +
            "select dd.deviceId from t_device_department dd " +
            "left join t_device d on dd.deviceId = d.id " +
            "left JOIN t_device_type dt ON d.type_id = dt.id" +
            "<where>\n" +
            "            <if test=\"query.keyword!=null and query.keyword!=''\">\n" +
            "                and (d.description like concat('%',#{keyword},'%') " +
            "or d.name like concat('%',#{keyword},'%') " +
            "or dt.name like concat('%',#{keyword},'%') " +
            "or dt.description like concat('%',#{keyword},'%') " +
            ")\n" +
            "            </if>\n" +
            "<if test=\"departmentId!=null and departmentId!=''\">\n" +
            "and dd.deptId=#{departmentId}" +
            "</if>" +
            "        </where>" +
            "" +
            "</script>")
    List<Long> selectDeviceIdByDepartmentId(DevicesQuery query, Long departmentId);

    List<Devices> selectDeviceByDepartmentId(DevicesQuery query, Long departmentId);

    @Select("select dd.deptId deptId,od.path deptPath from t_device_department dd left join t_org_department od on dd.deptId = od.id" +
            " where dd.deviceId = #{deviceId}")
    List<DeviceDeptDTO> selectDeptIdsByDeviceId(Long deviceId);
}
