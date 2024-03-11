package cn.pzhxy.devicemanager.statistics.mapper;

import cn.pzhxy.devicemanager.goods.domain.Goods;
import cn.pzhxy.devicemanager.statistics.Query.GoodsShowQuery;
import cn.pzhxy.devicemanager.statistics.vo.GoodsDataChangeStatisticVO;
import cn.pzhxy.devicemanager.statistics.vo.GoodsPriceChangeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface GoodsShowMapper {
    @Select("<script>SELECT dt.id as typeId, dt.name as typeName, SUM(d.count) as total " +
            "FROM t_goods d " +
            "left JOIN t_goods_type dt ON d.type_id = dt.id " +

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
    List<Map<String, Object>> getGoodsTypeCountsWithTypeName(GoodsShowQuery query);

    @Select("<script>SELECT dt.id as typeId, dt.name as typeName, SUM(d.useCount) as total " +
            "FROM t_goods d " +
            "left JOIN t_goods_type dt ON d.type_id = dt.id " +

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
    List<Map<String, Object>> getGoodsTypeUseCountsWithTypeName(GoodsShowQuery query);
    @Select("<script>SELECT count(*) " +
            "FROM t_goods d " +
            "left JOIN t_goods_type dt ON d.type_id = dt.id " +

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
    Long getGoodsTypeTotal(GoodsShowQuery query);

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
            "        SELECT * FROM t_goods ORDER BY createTime DESC\n" +
            "    ) t_goods, (SELECT @last_type_id := NULL, @type_id := NULL, @last_createTime := '0000-00-00 00:00:00') vars\n" +
            ") t\n" +
            "WHERE last_createTime IS NULL  " +
            "            <if test=\"keyword!=null and keyword!=''\">\n" +
            "                and t.name like concat('%',#{keyword},'%')\n" +
            "            </if></script>\n")
    Long queryTotal(GoodsShowQuery query);

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
            "        SELECT * FROM t_goods ORDER BY createTime DESC\n" +
            "    ) t_goods, (SELECT @last_type_id := NULL, @type_id := NULL, @last_createTime := '0000-00-00 00:00:00') vars\n" +
            ") t\n" +
            "WHERE last_createTime IS NULL \n " +
            "            <if test=\"keyword!=null and keyword!=''\">\n" +
            "                and t.name like concat('%',#{keyword},'%')\n" +
            "            </if>\n"+
            "limit #{start},#{pageSize} </script>")
    List<Goods> queryPageList(GoodsShowQuery query);

    @Select("select count(*) from t_goods_no_warehousing")
    Long selectGoodsTotal();

    @Select("select count(*) from t_goods")
    Long selectGoodsInTotal();

    @Select("select count(*) from t_goods_no_warehousing where status!=2")
    Long selectGoodsNotInTotal();

    @Select("SELECT IFNULL(SUM(count - useCount), 0) AS totalAvailableCount FROM t_goods;")
    Long selectGoodsCanUseTotal();

    // 按年统计
    @Select("SELECT DATE_FORMAT(createTime, '%Y') AS dimension, SUM(count) AS totalCount " +
            "FROM t_goods " +
            "WHERE DATE_FORMAT(createTime, '%Y') BETWEEN #{year} - 4 AND #{year} " +
            "GROUP BY DATE_FORMAT(createTime, '%Y')")
    List<GoodsDataChangeStatisticVO> getYearlyStatistics(@Param("year") int year);
    // 按月统计
    @Select("SELECT DATE_FORMAT(createTime, '%Y-%m') AS dimension, SUM(count) AS totalCount " +
            "FROM t_goods " +
            "WHERE YEAR(createTime) = #{year} " +
            "GROUP BY DATE_FORMAT(createTime, '%Y-%m')")
    List<GoodsDataChangeStatisticVO> getMonthlyStatistics(@Param("year") int year);
    // 按季度统计
    @Select("SELECT CONCAT(YEAR(createTime), '年第', QUARTER(createTime), '季度') AS dimension, SUM(count) AS totalCount " +
            "FROM t_goods " +
            "WHERE YEAR(createTime) = #{year} " +
            "GROUP BY CONCAT(YEAR(createTime), QUARTER(createTime))")
    List<GoodsDataChangeStatisticVO> getQuarterlyStatistics(@Param("year") int year);
    @Select("SELECT DATE_FORMAT(createTime, '%Y-%m-%d') AS dimension, SUM(count) AS totalCount " +
            "FROM t_goods " +
            "WHERE DATE_FORMAT(createTime, '%Y-%m') = '${year}-${month}' " +
            "GROUP BY DATE_FORMAT(createTime, '%Y-%m-%d')")
    List<GoodsDataChangeStatisticVO> getDailyStatistics(@Param("year") String year, @Param("month") String month);

    // 按年统计
    @Select("SELECT DATE_FORMAT(createTime, '%Y') AS dimension, SUM(useCount) AS totalUseCount " +
            "FROM t_goods " +
            "WHERE DATE_FORMAT(createTime, '%Y') = #{year} " +
            "GROUP BY DATE_FORMAT(createTime, '%Y')")
    List<GoodsDataChangeStatisticVO> getYearlyUsageStatistics(@Param("year") int year);
    // 按月统计
    @Select("SELECT DATE_FORMAT(createTime, '%Y-%m') AS dimension, SUM(useCount) AS totalUseCount " +
            "FROM t_goods " +
            "WHERE YEAR(createTime) = #{year} " +
            "GROUP BY DATE_FORMAT(createTime, '%Y-%m')")
    List<GoodsDataChangeStatisticVO> getMonthlyUsageStatistics(@Param("year") int year);
    // 按季度统计
    @Select("SELECT CONCAT(YEAR(createTime), '年第', QUARTER(createTime), '季度') AS dimension, SUM(useCount) AS totalUseCount " +
            "FROM t_goods " +
            "WHERE YEAR(createTime) = #{year} " +
            "GROUP BY CONCAT(YEAR(createTime), QUARTER(createTime))")
    List<GoodsDataChangeStatisticVO> getQuarterlyUsageStatistics(@Param("year") int year);
    // 按天统计
    @Select("SELECT DATE_FORMAT(createTime, '%Y-%m-%d') AS dimension, SUM(useCount) AS totalUseCount " +
            "FROM t_goods " +
            "WHERE DATE_FORMAT(createTime, '%Y-%m') = '${year}-${month}' " +
            "GROUP BY DATE_FORMAT(createTime, '%Y-%m-%d')")
    List<GoodsDataChangeStatisticVO> getDailyUsageStatistics(@Param("year") String year, @Param("month") String month);

    @Select("select createTime as date,price from t_goods where type_id = #{goodsId} ")
    List<GoodsPriceChangeVO> getGoodsPriceChangeVO(Long goodsId);
}