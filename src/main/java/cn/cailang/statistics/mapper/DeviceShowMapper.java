package cn.cailang.statistics.mapper;

import cn.cailang.statistics.Query.DeviceShowQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface DeviceShowMapper {
    @Select("<script>SELECT dt.id as typeId, dt.name as typeName, SUM(d.count) as total " +
            "FROM t_device d " +
            "left JOIN t_device_type dt ON d.type_id = dt.id " +

            "<where>\n" +
            "            <if test=\"keyword!=null and keyword!=''\">\n" +
            "                and (d.description like concat('%',#{keyword},'%') " +
            "or d.name like concat('%',#{keyword},'%') " +
            "or dt.name like concat('%',#{keyword},'%') " +
            "or dt.description like concat('%',#{keyword},'%') " +
            ")\n" +
            "            </if>\n" +
            "        </where>" +
            "GROUP BY dt.id, dt.name " +
            "limit #{start},#{pageSize} </script>")
    List<Map<String, Object>> getDeviceTypeCountsWithTypeName(DeviceShowQuery query);

    @Select("<script>SELECT count(*) " +
            "FROM t_device d " +
            "left JOIN t_device_type dt ON d.type_id = dt.id " +

            "<where>\n" +
            "            <if test=\"keyword!=null and keyword!=''\">\n" +
            "                and (d.description like concat('%',#{keyword},'%') " +
            "or d.name like concat('%',#{keyword},'%') " +
            "or dt.name like concat('%',#{keyword},'%') " +
            "or dt.description like concat('%',#{keyword},'%') " +
            ")\n" +
            "            </if>\n" +
            "        </where>" +
            "GROUP BY dt.id, dt.name " +
            "limit #{start},#{pageSize} </script>")
    Long getDeviceTypeTotal(DeviceShowQuery query);

}