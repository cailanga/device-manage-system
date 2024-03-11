package cn.pzhxy.devicemanager.base.mapper;

import cn.pzhxy.devicemanager.base.query.BaseQuery;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: BaseMapper
 * @Description: 公共mapper
 * @Author: 2026972757@qq.com
 * @Date: 2024/3/6 11:28
 * @Version 1.0
 **/
public interface BaseMapper<T> {
    /**
     * 根据id查询
     * @param id id
     * @return 信息
     */
    T selectById(Serializable id);

    /**
     * 查询所有信息
     * @return 信息列表
     */
    List<T> selectAll();

    /**
     * 新增信息
     * @param t 信息
     */
    void insert(T t);

    /**
     * 修改信息
     * @param t 信息
     */
    void update(T t);

    /**
     * 删除信息
     * @param id id
     */
    void deleteById(Serializable id);

    /**
     * 根据查询条件查询数据条数
     * @param query 查询条件
     * @return 数据总条数
     */
    Long queryTotal(BaseQuery query);

    /**
     * 根据查询条件查询信息
     * @param query 查询条件
     * @return 查询结果列表
     */
    List<T> queryPageList(BaseQuery query);

    /**
     * 根据ids批量删除
     * @param ids id集合
     */
    void batchDelete(List<Long> ids);
}
