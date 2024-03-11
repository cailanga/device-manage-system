package cn.pzhxy.devicemanager.notice.dto;

import lombok.Data;

/**
 * @ClassName: GoodsHandleDTO
 * @Description: TODO
 * @Author: lvjiaxin
 * @Date: 2024/2/8 19:22
 * @Version 1.0
 **/
@Data
public class NoticeHandleDTO {
    private Integer operateTypeId;
    private String description;
    private Long noticeId;
}
