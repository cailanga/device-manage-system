package cn.pzhxy.devicemanager.base.service.impl;

import cn.pzhxy.devicemanager.base.mapper.BaseMapper;
import cn.pzhxy.devicemanager.base.service.IBaseService;
import cn.pzhxy.devicemanager.base.query.BaseQuery;
import cn.pzhxy.devicemanager.base.utils.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: BaseServiceImpl
 * @Description: 公共service实现类
 * @Author: 2026972757@qq.com
 * @Date: 2024/3/6 11:47
 * @Version 1.0
 **/
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class BaseServiceImpl<T> implements IBaseService<T> {
    @Autowired
    private BaseMapper<T> baseMapper;

    @Override
    public T selectById(Serializable id) {
        return baseMapper.selectById(id);
    }

    @Override
    public List<T> selectAll() {
        return baseMapper.selectAll();
    }

    @Transactional
    @Override
    public void insert(T t) {
        baseMapper.insert(t);
    }

    @Transactional
    @Override
    public void update(T t) {
        baseMapper.update(t);
    }

    @Transactional
    @Override
    public void deleteById(Serializable id) {
        baseMapper.deleteById(id);
    }

    @Override
    public PageList<T> pageList(BaseQuery query) {
        Long total = baseMapper.queryTotal(query);
        if(total>0){
            List<T> rows = baseMapper.queryPageList(query);
            return new PageList<>(total,rows);
        }
        return new PageList<>();
    }

    @Transactional
    @Override
    public void batchDelete(List<Long> ids) {
        if(ids==null || ids.isEmpty()){
            throw new RuntimeException("请选择数据后再删除！");
        }
        baseMapper.batchDelete(ids);
    }

}
