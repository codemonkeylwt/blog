package ink.casual.common.mapper;

import ink.casual.common.model.SysConf;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lwt
 * @date 2019/4/14 13:45
 */
public interface SysConfMapper {

    @Select("select * from sys_conf where enable = 1")
    List<SysConf> queryAllConf();

    @Select("select * from sys_conf where enable = 1 and key = #{key}")
    SysConf queryConfByKey(String key);

    @Insert("INSERT INTO sys_conf(`key`, `value`, `description`,`enable`) VALUES (#{key}, #{value}, #{description},1) " +
            "ON DUPLICATE KEY UPDATE `key` = #{key},`value` = #{value},`description` = #{description},`enable` = #{enable}")
    void insertOrUpdateSysConf(SysConf sysConf);
}
