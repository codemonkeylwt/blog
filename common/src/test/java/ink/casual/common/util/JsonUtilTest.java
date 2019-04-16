package ink.casual.common.util;

import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * @author lwt
 * @date 2019/4/16 13:54
 */
public class JsonUtilTest {

    private JSONObject jsonObject;

    @Before
    public void setJsonObect() {
        jsonObject = new JSONObject();
        jsonObject.put("name", "lwt");
        jsonObject.put("date", new Date(1555394412055L));
        jsonObject.put("age", 24);
    }

    @Test
    public void toJsonString() {
        Assert.assertEquals(JsonUtil.toJsonString(jsonObject), "{\"age\":24,\"date\":\"2019-04-16 14:00:12\",\"name\":\"lwt\"}");
    }

    @Test
    public void toJsonString1() {
        Assert.assertEquals(JsonUtil.toJsonString("yyyy-MM-dd", jsonObject), "{\"age\":24,\"date\":\"2019-04-16\",\"name\":\"lwt\"}");
    }

    @Test
    public void toJsonString2() {
        Assert.assertEquals(JsonUtil.toJsonString(jsonObject,"age"),"{\"date\":\"2019-04-16 14:00:12\",\"name\":\"lwt\"}");
    }

    @Test
    public void toJsonString3() {
        Assert.assertEquals(JsonUtil.toJsonString("yyyy-MM-dd", jsonObject, "age"),"{\"date\":\"2019-04-16\",\"name\":\"lwt\"}");
    }
}