package cn.cailang.base.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: BaseQuery
 * @Description: 基础查询对象
 * @Author: 3299903308@qq.com
 * @Date: 2023/4/24 11:46
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseQuery {
    //当前页
    private Integer currentPage;
    //页数
    private Integer pageSize;
    //关键字
    private String keyword;

    /**
     * 获取分页查询的起始位置
     * @return 返回起始位置
     */
    public Integer getStart() {
        return (this.currentPage-1) * this.pageSize;
    }
}
