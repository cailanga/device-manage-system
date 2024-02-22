package cn.cailang.sys.mapper;

import cn.cailang.sys.domain.DictionaryItem;
import cn.cailang.base.mapper.BaseMapper;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cailang
 * @since 2023-05-09
 */
public interface DictionaryitemMapper extends BaseMapper<DictionaryItem> {
    /**
     * 根据父级数据字段删除数据字典明细
     * @param pid 数据字典id
     */
    void deleteByPid(Serializable pid);

    /**
     * 根据父级数据字段批量删除数据字典明细
     * @param pids 数据字典ids集合
     */
    void deleteByPids(List<Long> pids);

    List<DictionaryItem> selectBySn(String sn);
}
