package cn.cailang.org.mapper;

import cn.cailang.base.mapper.BaseMapper;
import cn.cailang.org.domain.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper extends BaseMapper<Employee> {

    /**
     * 设置角色权限
     * @param employeeId 员工id
     * @param roleIds 角色id集合
     */
    void setEmployeeRole(@Param("employeeId") Long employeeId,@Param("roleIds") List<Long> roleIds);

    /**
     * 根据员工id删除员工角色表相关角色信息
     * @param employeeId 员工id
     */
    void deleteEmpRoleByEmployeeId(Long employeeId);

    /**
     * 根据角色id获取其角色的ids
     * @param employeeId 员工id
     * @return ids集合
     */
    List<Long> getEmpRolesByEmployeeId(Long employeeId);

    /**
     * 通过username查询员工信息
     * @param username 用户名
     * @return 员工信息
     */
    Employee selectByUsername(String username);

    /**
     * 通过员工id查询sn
     * @param id 员工id
     * @return sn集合
     */
    List<String> selectSnsByEmployeeId(Long id);
}
