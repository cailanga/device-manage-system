package cn.pzhxy.devicemanager.sys.mapper;

import cn.pzhxy.devicemanager.sys.domain.SysScheduledCron;
import cn.pzhxy.devicemanager.sys.query.SysScheduledCronQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SysScheduledCronMapper {
    @Select("select * from t_sys_scheduledcron")
    List<SysScheduledCron> findAll();
    @Select("select * from t_sys_scheduledcron where cronKey=#{cronKey}")
    SysScheduledCron findByCronKey(String cronKey);
    //更新定时任务cron表达式
    @Update(value = "update t_sys_scheduledcron set cronExpression=#{cronExpression},taskExplain=#{taskExplain} where cronKey=#{cronKey}")
    int updateCronExpression(SysScheduledCron sysScheduledCron);
    //更新定时任务状态
    @Update(value = "update t_sys_scheduledcron set taskStatus=#{status} where cronKey=#{cronKey}")
    int updateTaskStatusByCronKey(Integer status, String cronKey);

    @Insert("insert into t_sys_scheduledcron values(default,#{cronKey},#{cronExpression},#{taskExplain},#{taskStatus})")
    int insertCron(SysScheduledCron sysScheduledCron);
    /**
     * 根据查询条件查询数据条数
     * @param query 查询条件
     * @return 数据总条数
     */
    @Select("select count(*) from t_sys_scheduledcron")
    Long queryTotal(SysScheduledCronQuery query);

    /**
     * 根据查询条件查询信息
     * @param query 查询条件
     * @return 查询结果列表
     */
    @Select("select * from t_sys_scheduledcron limit #{start},#{pageSize}")
    List<SysScheduledCron> queryPageList(SysScheduledCronQuery query);

    @Delete("delete from t_sys_scheduledcron where cronId=#{id}")
    void deleteById(Long id);

    @Select("select * from t_sys_scheduledcron where cronId = #{id}")
    SysScheduledCron findById(Long id);
}
