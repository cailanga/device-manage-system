package cn.cailang.sys.domain;

import cn.cailang.base.domain.BaseDomain;

import lombok.Data;


/**
 * <p>
 *  数据字典明细
 * </p>
 *
 * @author cailang
 * @since 2023-05-09
 */
@Data
public class DictionaryItem extends BaseDomain{

    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String value;
    private Integer sequence;
    private String intro;
    /**
     * 数据字典类型id
     */
    private Long parentId;
    private Dictionary parent;
}
