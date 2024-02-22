package cn.cailang.sys.domain;

import java.math.BigDecimal;
import java.util.Date;
import cn.cailang.base.domain.BaseDomain;

import lombok.Data;


/**
 * <p>
 * 
 * </p>
 *
 * @author cailang
 * @since 2023-05-09
 */
@Data
public class Config extends BaseDomain{

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 配置信息的key
     */
    private String key;
    /**
     * 配置信息的值
     */
    private String value;
    private Long type;
    /**
     * 简介
     */
    private String intro;
}
