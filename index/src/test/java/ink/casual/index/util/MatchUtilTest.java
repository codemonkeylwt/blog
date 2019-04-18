package ink.casual.index.util;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author lwt
 * @date 2019/4/17 11:05
 */
public class MatchUtilTest {

    @Test
    public void matchAccountName() {
        Assert.assertTrue(MatchUtil.matchAccountName("sdfa2132"));
    }

    @Test
    public void matchEmail() {
        Assert.assertTrue(MatchUtil.matchEmail("service@xsoftlab.net"));
    }

    @Test
    public void matchMobile() {
        Assert.assertTrue(MatchUtil.matchMobile("13356642648"));
    }

}