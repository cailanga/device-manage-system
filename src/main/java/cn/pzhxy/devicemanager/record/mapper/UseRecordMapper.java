package cn.pzhxy.devicemanager.record.mapper;

import cn.pzhxy.devicemanager.base.mapper.BaseMapper;
import cn.pzhxy.devicemanager.record.domain.UseRecord;
import cn.pzhxy.devicemanager.record.dto.UseRecordPageInfoDTO;

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
