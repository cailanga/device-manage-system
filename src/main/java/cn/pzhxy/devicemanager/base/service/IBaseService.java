package cn.pzhxy.devicemanager.base.service;

import cn.pzhxy.devicemanager.base.query.BaseQuery;
import cn.pzhxy.devicemanager.base.utils.PageList;

import java.io.Serializable;
import java.util.List;

/**
 * service公共接口
 * @author 32999
 * @param <T>
 */
public interface IBaseService<T> {
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
     * 根据查询对象查询分页数据
     * @param query 查询条件对象
     * @return 分页结果
     */
    PageList<T> pageList(BaseQuery query);

    /**
     * 根据ids批量删除
     * @param ids id集合
     */
    void batchDelete(List<Long> ids);

}
