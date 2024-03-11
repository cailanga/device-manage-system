package cn.pzhxy.devicemanager.goods.mapper;

import cn.pzhxy.devicemanager.base.mapper.BaseMapper;
import cn.pzhxy.devicemanager.goods.domain.UseRecord;
import cn.pzhxy.devicemanager.goods.dto.UseRecordPageInfoDTO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */
public interface UseRecordMapper extends BaseMapper<UseRecord> {

    List<UseRecord> selectAllForOperator(UseRecordPageInfoDTO dto);

    Long selectCountForOperator(UseRecordPageInfoDTO dto);
}
