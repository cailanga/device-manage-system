package cn.pzhxy.devicemanager.goods.service;

import cn.pzhxy.devicemanager.base.service.IBaseService;
import cn.pzhxy.devicemanager.base.utils.PageList;
import cn.pzhxy.devicemanager.goods.domain.UseRecord;
import cn.pzhxy.devicemanager.goods.dto.UseRecordPageInfoDTO;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface IUseRecordService extends IBaseService<UseRecord> {

    PageList selectAllForOperator(UseRecordPageInfoDTO dto);
}
