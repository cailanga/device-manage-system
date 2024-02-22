package cn.cailang.sys.service.impl;

import cn.cailang.sys.domain.Dictionary;
import cn.cailang.sys.mapper.DictionaryitemMapper;
import cn.cailang.sys.service.IDictionaryService;
import cn.cailang.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cailang
 * @since 2023-05-09
 */
@Service
public class DictionaryServiceImpl extends BaseServiceImpl<Dictionary> implements IDictionaryService {
    @Autowired
    private DictionaryitemMapper dictionaryitemMapper;
    @Transactional
    @Override
    public void deleteById(Serializable id) {
        //删除的时候要删除数据字典明细
        dictionaryitemMapper.deleteByPid(id);
        super.deleteById(id);
    }

    @Transactional
    @Override
    public void batchDelete(List<Long> ids) {
        //删除的时候要删除数据字典明细
        dictionaryitemMapper.deleteByPids(ids);
        super.batchDelete(ids);
    }
}
