package cn.pzhxy.devicemanager.notice.dto;

import lombok.Data;

import java.util.List;

/**
 * @ClassName: NoticeRoleDTO
 * @Description: TODO
 * @Author: lvjiaxin
 * @Date: 2024/3/8 18:57
 * @Version 1.0
 **/
@Data
public class NoticeRoleDTO {
    private Long noticeId;
    private List<Long> roleIds;
}
