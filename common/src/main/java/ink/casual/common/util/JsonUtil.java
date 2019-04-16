package ink.casual.common.util;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import java.util.Collections;
import java.util.Set;

/**
 * @author lwt
 * @date 2019/4/16 13:38
 */
public class JsonUtil {

    private static String DEFAULT_DATEFORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * object转json
     *
     */
    public static String toJsonString(Object value){
        return JSONObject.toJSONString(value, SerializeConfig.globalInstance, null,DEFAULT_DATEFORMAT,1, SerializerFeature.MapSortField);
    }

    /**
     * object转json,排除指定字段
     *
     */
    public static String toJsonString(Object value,String... excludeField){
        SimplePropertyPreFilter[] filters = {new SimplePropertyPreFilter()};
        Set<String> excludes = filters[0].getExcludes();
        Collections.addAll(excludes, excludeField);
        return JSONObject.toJSONString(value, SerializeConfig.globalInstance, filters,DEFAULT_DATEFORMAT,1, SerializerFeature.MapSortField);
    }

    /**
     * object转json,自定义时间格式化,排除指定字段
     *
     */
    public static String toJsonString(String dateFormat,Object value,String... excludeField){
        SimplePropertyPreFilter[] filters = {new SimplePropertyPreFilter()};
        Set<String> excludes = filters[0].getExcludes();
        Collections.addAll(excludes, excludeField);
        return JSONObject.toJSONString(value, SerializeConfig.globalInstance, filters,dateFormat,1, SerializerFeature.MapSortField);
    }

}
