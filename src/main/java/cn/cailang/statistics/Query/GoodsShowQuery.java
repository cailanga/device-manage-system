package cn.cailang.statistics.Query;

import cn.cailang.base.query.BaseQuery;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName: DeviceShowQuery
 * @Description: TODO
 * @Author: cailang
 * @Date: 2024/2/28 18:00
 * @Version 1.0
 **/
@Data
public class GoodsShowQuery extends BaseQuery {
    @Range(min = 1,max =9999,message = "年份超过范围")
    private Integer year;
    @Range(min = 1,max =12,message = "月份超过范围")
    private Integer month;
    @NotBlank(message = "查询类型不能为空")
    private String type;
}
