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
public class Dictionary extends BaseDomain{

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 标识
     */
    private String sn;
    /**
     * 标题
     */
    private String title;
    /**
     * 描述
     */
    private String intro;
    private Integer status;
}
