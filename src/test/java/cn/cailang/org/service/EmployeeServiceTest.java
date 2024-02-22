package cn.cailang.org.service;

import cn.cailang.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeServiceTest extends BaseTest {
    @Autowired
    private IEmployeeService employeeService;

    @Test
    public void selectById() {
//        System.out.println(employeeService.selectById(43));
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
//        employeeService.deleteById(44);
    }
}