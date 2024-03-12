package cn.pzhxy.devicemanager.goods.mapper;

import cn.pzhxy.devicemanager.goods.domain.Goods;
import cn.pzhxy.devicemanager.goods.dto.GoodsDeptDTO;
import cn.pzhxy.devicemanager.goods.query.GoodsQuery;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName: GoodsDepartmentMapper
 * @Description: TODO
 * @Author: cailang
 * @Date: 2024/3/12 16:45
 * @Version 1.0
 **/
@Mapper
public interface GoodsDepartmentMapper {
    @Delete("delete from t_goods_department where goodsId=#{goodsId}")
    public void deleteByGoodsId(Long goodsId);
    @Insert("insert into t_goods_department(goodsId, deptId) values(#{goodsId}, #{deptId})")
    void insert(GoodsDeptDTO goodsDeptDTO);

    @Select("<script>" +
            "select dd.goodsId from t_goods_department dd " +
            "left join t_goods d on dd.goodsId = d.id " +
            "left JOIN t_goods_type dt ON d.type_id = dt.id" +
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
    List<Long> selectGoodsIdByDepartmentId(GoodsQuery query, Long departmentId);

    List<Goods> selectGoodsByDepartmentId(GoodsQuery query, Long departmentId);

    @Select("select dd.deptId deptId,od.path deptPath from t_goods_department dd left join t_org_department od on dd.deptId = od.id" +
            " where dd.goodsId = #{goodsId}")
    List<GoodsDeptDTO> selectDeptIdsByGoodsId(Long goodsId);
}
