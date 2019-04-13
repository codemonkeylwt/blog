package ink.casual.common.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Arrays;

/**
 * @author lwt
 * @date 2019/4/13 17:16
 */
@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 存值
     * 过期时间默认5分钟
     *
     */
    public void set(String key,Object value){
        redisTemplate.opsForValue().set(key,JSONObject.toJSONString(value),Duration.ofMinutes(5));
    }

    /**
     * 存值
     * 可以指定默认时间
     *
     */
    public void set(String key, Object value, Duration exp){
        redisTemplate.opsForValue().set(key,JSONObject.toJSONString(value),exp);
    }

    /**
     * 取值
     * 默认返回String
     *
     */
    public String get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 取值
     * 返回相应类型
     *
     */
    public <T>T get(String key,Class<T> tClass){
        return JSONObject.parseObject(redisTemplate.opsForValue().get(key),tClass);
    }


    /**
     * 获得系统参数
     *
     */
    public String getSysValue(String key){
        String value = get(key);
        if(value == null){
            /*
             * TODO
             * 2019/4/13
             * lwt
             * 从数据库读取系统参数
             */
            return null;
        }else {
            return value;
        }
    }

    /**
     * 获得系统参数
     *
     */
    public <T>T getSysValue(String key,Class<T> tClass){
        T t = get(key, tClass);
        if (t == null){
            /*
             * TODO
             * 2019/4/13
             * lwt
             * 从数据库读取系统参数
             */
            return null;
        }else {
            return t;
        }
    }

    /**
     * 设置系统参数
     *
     */
    public void setSysValue(String key,Object value){
        redisTemplate.opsForValue().set(key, JSONObject.toJSONString(value));
    }

    /**
     * 删除键值对
     *
     */
    public void deleteKey(String... key){
        if (key.length == 1) {
            redisTemplate.delete(key[0]);
        }else {
            redisTemplate.delete(Arrays.asList(key));
        }
    }

}
