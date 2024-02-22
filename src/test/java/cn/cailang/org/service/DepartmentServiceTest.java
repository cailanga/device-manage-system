package cn.cailang.org.service;

import cn.cailang.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class DepartmentServiceTest extends BaseTest {
    @Autowired
    private IDepartmentService departmentService;

    @Test
    public void selectById() {
        System.out.println(departmentService.selectById(1));
    }

    @Test
    public void selectAll() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void update() {
    }

    @Test
    public void deleteById() {
//        departmentService.deleteById(44);
    }
}