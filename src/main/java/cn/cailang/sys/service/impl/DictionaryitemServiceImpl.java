package cn.cailang.sys.service.impl;

import cn.cailang.sys.domain.DictionaryItem;
import cn.cailang.sys.mapper.DictionaryitemMapper;
import cn.cailang.sys.service.IDictionaryitemService;
import cn.cailang.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class DictionaryitemServiceImpl extends BaseServiceImpl<DictionaryItem> implements IDictionaryitemService {

    @Autowired
    private DictionaryitemMapper dictionaryitemMapper;
    @Override
    public List<DictionaryItem> selectBySn(String sn) {
        return dictionaryitemMapper.selectBySn(sn);
    }
}
