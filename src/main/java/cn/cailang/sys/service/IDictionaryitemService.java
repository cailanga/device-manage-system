package cn.cailang.sys.service;

import cn.cailang.sys.domain.DictionaryItem;
import cn.cailang.base.service.IBaseService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cailang
 * @since 2023-05-09
 */
public interface IDictionaryitemService extends IBaseService<DictionaryItem> {

    List<DictionaryItem> selectBySn(String sn);
}
