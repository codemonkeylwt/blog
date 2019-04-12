package ink.casual.user.mybatis;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author lwt
 * @date 2019/4/12 14:57
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
