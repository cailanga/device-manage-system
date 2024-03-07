package cn.cailang.statistics.mapper;

import cn.cailang.base.query.BaseQuery;
import cn.cailang.device.domain.Devices;
import cn.cailang.statistics.Query.DeviceShowQuery;
import cn.cailang.statistics.vo.DeviceDataChangeStatisticVO;
import cn.cailang.statistics.vo.DevicePriceChangeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    @Select("<script>SELECT dt.id as typeId, dt.name as typeName, SUM(d.useCount) as total " +
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
    List<Map<String, Object>> getDeviceTypeUseCountsWithTypeName(DeviceShowQuery query);
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
            "</script>")
    Long getDeviceTypeTotal(DeviceShowQuery query);

    /**
     * 根据查询条件查询数据条数
     * @param query 查询条件
     * @return 数据总条数
     */
    @Select("<script>SELECT count(*)\n" +
            "FROM (\n" +
            "    SELECT *,\n" +
            "           @last_type_id := IF(@type_id = type_id, @last_createTime := createTime, NULL) AS last_createTime,\n" +
            "           @type_id := type_id,\n" +
            "           @last_createTime := createTime\n" +
            "    FROM (\n" +
            "        SELECT * FROM t_device ORDER BY createTime DESC\n" +
            "    ) t_device, (SELECT @last_type_id := NULL, @type_id := NULL, @last_createTime := '0000-00-00 00:00:00') vars\n" +
            ") t\n" +
            "WHERE last_createTime IS NULL  " +
            "            <if test=\"keyword!=null and keyword!=''\">\n" +
            "                and t.name like concat('%',#{keyword},'%')\n" +
            "            </if></script>\n")
    Long queryTotal(DeviceShowQuery query);

    /**
     * 根据查询条件查询信息
     * @param query 查询条件
     * @return 查询结果列表
     */
    @Select("<script>SELECT t.* \n" +
            "FROM (\n" +
            "    SELECT *,\n" +
            "           @last_type_id := IF(@type_id = type_id, @last_createTime := createTime, NULL) AS last_createTime,\n" +
            "           @type_id := type_id,\n" +
            "           @last_createTime := createTime\n" +
            "    FROM (\n" +
            "        SELECT * FROM t_device ORDER BY createTime DESC\n" +
            "    ) t_device, (SELECT @last_type_id := NULL, @type_id := NULL, @last_createTime := '0000-00-00 00:00:00') vars\n" +
            ") t\n" +
            "WHERE last_createTime IS NULL \n " +
            "            <if test=\"keyword!=null and keyword!=''\">\n" +
            "                and t.name like concat('%',#{keyword},'%')\n" +
            "            </if>\n"+
            "limit #{start},#{pageSize} </script>")
    List<Devices> queryPageList(DeviceShowQuery query);

    @Select("select count(*) from t_device_no_warehousing")
    Long selectDeviceTotal();

    @Select("select count(*) from t_device")
    Long selectDeviceInTotal();

    @Select("select count(*) from t_device_no_warehousing where status!=2")
    Long selectDeviceNotInTotal();

    @Select("SELECT COUNT(goodsId) FROM t_disable;")
    Long selectDeviceDisabledTotal();

    @Select("SELECT IFNULL(SUM(count - useCount), 0) AS totalAvailableCount FROM t_device;")
    Long selectDeviceCanUseTotal();

    // 按年统计
    @Select("SELECT DATE_FORMAT(createTime, '%Y') AS dimension, SUM(count) AS totalCount " +
            "FROM t_device " +
            "WHERE DATE_FORMAT(createTime, '%Y') BETWEEN #{year} - 4 AND #{year} " +
            "GROUP BY DATE_FORMAT(createTime, '%Y')")
    List<DeviceDataChangeStatisticVO> getYearlyStatistics(@Param("year") int year);
    // 按月统计
    @Select("SELECT DATE_FORMAT(createTime, '%Y-%m') AS dimension, SUM(count) AS totalCount " +
            "FROM t_device " +
            "WHERE YEAR(createTime) = #{year} " +
            "GROUP BY DATE_FORMAT(createTime, '%Y-%m')")
    List<DeviceDataChangeStatisticVO> getMonthlyStatistics(@Param("year") int year);
    // 按季度统计
    @Select("SELECT CONCAT(YEAR(createTime), '年第', QUARTER(createTime), '季度') AS dimension, SUM(count) AS totalCount " +
            "FROM t_device " +
            "WHERE YEAR(createTime) = #{year} " +
            "GROUP BY CONCAT(YEAR(createTime), QUARTER(createTime))")
    List<DeviceDataChangeStatisticVO> getQuarterlyStatistics(@Param("year") int year);
    @Select("SELECT DATE_FORMAT(createTime, '%Y-%m-%d') AS dimension, SUM(count) AS totalCount " +
            "FROM t_device " +
            "WHERE DATE_FORMAT(createTime, '%Y-%m') = '${year}-${month}' " +
            "GROUP BY DATE_FORMAT(createTime, '%Y-%m-%d')")
    List<DeviceDataChangeStatisticVO> getDailyStatistics(@Param("year") String year, @Param("month") String month);

    // 按年统计
    @Select("SELECT DATE_FORMAT(createTime, '%Y') AS dimension, SUM(useCount) AS totalUseCount " +
            "FROM t_device " +
            "WHERE DATE_FORMAT(createTime, '%Y') = #{year} " +
            "GROUP BY DATE_FORMAT(createTime, '%Y')")
    List<DeviceDataChangeStatisticVO> getYearlyUsageStatistics(@Param("year") int year);
    // 按月统计
    @Select("SELECT DATE_FORMAT(createTime, '%Y-%m') AS dimension, SUM(useCount) AS totalUseCount " +
            "FROM t_device " +
            "WHERE YEAR(createTime) = #{year} " +
            "GROUP BY DATE_FORMAT(createTime, '%Y-%m')")
    List<DeviceDataChangeStatisticVO> getMonthlyUsageStatistics(@Param("year") int year);
    // 按季度统计
    @Select("SELECT CONCAT(YEAR(createTime), '年第', QUARTER(createTime), '季度') AS dimension, SUM(useCount) AS totalUseCount " +
            "FROM t_device " +
            "WHERE YEAR(createTime) = #{year} " +
            "GROUP BY CONCAT(YEAR(createTime), QUARTER(createTime))")
    List<DeviceDataChangeStatisticVO> getQuarterlyUsageStatistics(@Param("year") int year);
    // 按天统计
    @Select("SELECT DATE_FORMAT(createTime, '%Y-%m-%d') AS dimension, SUM(useCount) AS totalUseCount " +
            "FROM t_device " +
            "WHERE DATE_FORMAT(createTime, '%Y-%m') = '${year}-${month}' " +
            "GROUP BY DATE_FORMAT(createTime, '%Y-%m-%d')")
    List<DeviceDataChangeStatisticVO> getDailyUsageStatistics(@Param("year") String year, @Param("month") String month);

    // 按年统计
    @Select("SELECT DATE_FORMAT(date, '%Y') AS dimension, COUNT(*) AS totalUseCount " +
            "FROM t_disable " +
            "WHERE DATE_FORMAT(date, '%Y') = #{year} " +
            "GROUP BY DATE_FORMAT(date, '%Y')")
    List<DeviceDataChangeStatisticVO> getYearlyDisabledStatistics(@Param("year") int year);
    // 按月统计
    @Select("SELECT DATE_FORMAT(date, '%Y-%m') AS dimension, COUNT(*) AS totalUseCount " +
            "FROM t_disable " +
            "WHERE YEAR(createTime) = #{year} " +
            "GROUP BY DATE_FORMAT(date, '%Y-%m')")
    List<DeviceDataChangeStatisticVO> getMonthlyDisabledStatistics(@Param("year") int year);
    // 按季度统计
    @Select("SELECT CONCAT(YEAR(date), '年第', QUARTER(date), '季度') AS dimension, COUNT(*) AS totalUseCount " +
            "FROM t_disable " +
            "WHERE YEAR(date) = #{year} " +
            "GROUP BY CONCAT(YEAR(date), QUARTER(date))")
    List<DeviceDataChangeStatisticVO> getQuarterlyDisabledStatistics(@Param("year") int year);
    // 按天统计
    @Select("SELECT DATE_FORMAT(date, '%Y-%m-%d') AS dimension, COUNT(*) AS totalUseCount " +
            "FROM t_disable " +
            "WHERE DATE_FORMAT(date, '%Y-%m') = '${year}-${month}' " +
            "GROUP BY DATE_FORMAT(date, '%Y-%m-%d')")
    List<DeviceDataChangeStatisticVO> getDailyDisabledStatistics(@Param("year") String year, @Param("month") String month);

    // 按年统计
    @Select("SELECT DATE_FORMAT(date, '%Y') AS dimension, COUNT(*) AS totalUseCount " +
            "FROM t_maintain " +
            "WHERE DATE_FORMAT(date, '%Y') = #{year} " +
            "GROUP BY DATE_FORMAT(date, '%Y')")
    List<DeviceDataChangeStatisticVO> getYearlyMaintainStatistics(@Param("year") int year);
    // 按月统计
    @Select("SELECT DATE_FORMAT(date, '%Y-%m') AS dimension, COUNT(*) AS totalUseCount " +
            "FROM t_maintain " +
            "WHERE YEAR(createTime) = #{year} " +
            "GROUP BY DATE_FORMAT(date, '%Y-%m')")
    List<DeviceDataChangeStatisticVO> getMonthlyMaintainStatistics(@Param("year") int year);
    // 按季度统计
    @Select("SELECT CONCAT(YEAR(date), '年第', QUARTER(date), '季度') AS dimension, COUNT(*) AS totalUseCount " +
            "FROM t_maintain " +
            "WHERE YEAR(date) = #{year} " +
            "GROUP BY CONCAT(YEAR(date), QUARTER(date))")
    List<DeviceDataChangeStatisticVO> getQuarterlyMaintainStatistics(@Param("year") int year);
    // 按天统计
    @Select("SELECT DATE_FORMAT(date, '%Y-%m-%d') AS dimension, COUNT(*) AS totalUseCount " +
            "FROM t_maintain " +
            "WHERE DATE_FORMAT(date, '%Y-%m') = '${year}-${month}' " +
            "GROUP BY DATE_FORMAT(date, '%Y-%m-%d')")
    List<DeviceDataChangeStatisticVO> getDailyMaintainStatistics(@Param("year") String year, @Param("month") String month);

    @Select("select createTime as date,price from t_device where type_id = #{deviceId} ")
    List<DevicePriceChangeVO> getDevicePriceChangeVO(Long deviceId);
}