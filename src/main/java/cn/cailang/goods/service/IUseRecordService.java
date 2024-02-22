package cn.cailang.goods.service;

import cn.cailang.base.service.IBaseService;
import cn.cailang.base.utils.PageList;
import cn.cailang.goods.domain.Goods;
import cn.cailang.goods.domain.UseRecord;
import cn.cailang.goods.dto.UseRecordPageInfoDTO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface IUseRecordService extends IBaseService<UseRecord> {

    PageList selectAllForOperator(UseRecordPageInfoDTO dto);
}
