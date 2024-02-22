package cn.cailang.goods.mapper;

import cn.cailang.base.mapper.BaseMapper;
import cn.cailang.goods.domain.Goods;
import cn.cailang.goods.domain.UseRecord;
import cn.cailang.goods.dto.UseRecordPageInfoDTO;

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
