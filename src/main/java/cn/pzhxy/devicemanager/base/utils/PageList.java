package cn.pzhxy.devicemanager.base.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: PageList
 * @Description: 分页查询返回数据封装
 * @Author: 2026972757@qq.com
 * @Date: 2024/2/24 12:36
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageList<T> {

    private Long total = 0L;
    private List<T> rows = new ArrayList<>();
}
