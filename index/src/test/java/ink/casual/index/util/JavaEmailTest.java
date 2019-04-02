package ink.casual.index.util;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author lwt
 * @date 2019/4/2 11:59
 */
public class JavaEmailTest {

    @Test
    @Ignore
    public void sendEmail() {
        System.out.println(JavaEmail.sendEmail("365329203@qq.com", "test", "test"));
    }
}