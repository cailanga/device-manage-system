package cn.cailang.notice.dto;

import lombok.Data;

/**
 * @ClassName: GoodsHandleDTO
 * @Description: TODO
 * @Author: cailang
 * @Date: 2024/2/8 19:22
 * @Version 1.0
 **/
@Data
public class NoticeHandleDTO {
    private Integer operateTypeId;
    private String description;
    private Long noticeId;
}
