package ink.casual.common.util;

import com.alibaba.fastjson.JSONObject;
import ink.casual.common.mapper.SysConfMapper;
import ink.casual.common.model.SysConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

/**
 * @author lwt
 * @date 2019/4/13 17:16
 */
@Service
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;
    private final SysConfMapper sysConfMapper;

    @Autowired
    public RedisService(RedisTemplate<String, String> redisTemplate, SysConfMapper sysConfMapper) {
        this.redisTemplate = redisTemplate;
        this.sysConfMapper = sysConfMapper;
    }

    /**
     * 存值
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, JsonUtil.toJsonString(value));
    }

    /**
     * 存值
     * 可以指定默认时间
     */
    public void set(String key, Object value, Duration exp) {
        redisTemplate.opsForValue().set(key, JsonUtil.toJsonString(value), exp);
    }

    /**
     * 取值
     * 默认返回String
     */
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 取值
     * 返回相应类型
     */
    public <T> T get(String key, Class<T> tClass) {
        return JSONObject.parseObject(redisTemplate.opsForValue().get(key), tClass);
    }


    /**
     * 获得系统参数
     */
    public String getSysValue(String key) {
        String value = get(key);
        if (value == null) {
            SysConf sysConf = sysConfMapper.queryConfByKey(key);
            if (sysConf != null) {
                set(key, sysConf.getValue());
                return sysConf.getValue();
            } else {
                return null;
            }
        } else {
            return value;
        }
    }

    /**
     * 获得系统参数
     */
    public <T> T getSysValue(String key, Class<T> tClass) {
        T t = get(key, tClass);
        if (t == null) {
            SysConf sysConf = sysConfMapper.queryConfByKey(key);
            if (sysConf != null) {
                set(key, sysConf.getValue());
                return JSONObject.parseObject(sysConf.getValue(), tClass);
            } else {
                return null;
            }
        } else {
            return t;
        }
    }

    /**
     * 设置系统参数
     */
    public void setSysValue(String key, Object value) {
        set(key, value);
    }

    /**
     * 重置系统参数
     */
    public void reSetSysConf() {
        List<SysConf> sysConfs = sysConfMapper.queryAllConf();
        for (SysConf sysConf : sysConfs) {
            set(sysConf.getKey(), sysConf.getValue());
        }
    }

    /**
     * 判断是否存在key
     */
    public boolean hasKey(String key) {
        Boolean var = redisTemplate.hasKey(key);
        return var == null ? false : var;
    }

    /**
     * 删除键值对
     */
    public void deleteKey(String... key) {
        if (key.length == 1) {
            redisTemplate.delete(key[0]);
        } else {
            redisTemplate.delete(Arrays.asList(key));
        }
    }

    /**
     * 锁定key，锁定成功返回true
     */
    public Boolean lock(String key, Duration duration) {
        if (hasKey(key)) {
            return false;
        } else {
            set(key, "locking", duration);
            return true;
        }
    }

    /**
     * 解锁key
     */
    public void unlock(String key) {
        deleteKey(key);
    }

}
