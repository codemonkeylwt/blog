import ink.casual.common.util.RSAUtil;
import org.junit.Before;
import org.junit.Test;

import java.security.Key;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * @author lwt
 * @date 2019/4/13 10:29
 */
public class RSATest {
    private String publicKey;
    private String privateKey;

    @Before
    public void setUp() {
        Map<String, Key> keyMap = RSAUtil.initKey();
        publicKey = RSAUtil.getPublicKey(keyMap);
        privateKey = RSAUtil.getPrivateKey(keyMap);
        System.err.println("公钥: \n\r" + publicKey);
        System.err.println("私钥： \n\r" + privateKey);
    }

    @Test
    public void test() {
        System.err.println("公钥加密——私钥解密");
        String inputStr = "dounine";
        byte[] encodedData = RSAUtil.encryptByPublicKey(inputStr, publicKey);
        byte[] decodedData = RSAUtil.decryptByPrivateKey(encodedData, privateKey);
        String outputStr = new String(decodedData);
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
        assertEquals(inputStr, outputStr);
    }

    @Test
    public void testSign() {
        System.err.println("私钥加密——公钥解密");
        String inputStr = "dounine";
        byte[] data = inputStr.getBytes();
        byte[] encodedData = RSAUtil.encryptByPrivateKey(data, privateKey);
        byte[] decodedData = RSAUtil.decryptByPublicKey(encodedData, publicKey);
        String outputStr = new String(decodedData);
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
        assertEquals(inputStr, outputStr);
        System.err.println("私钥签名——公钥验证签名");
        // 产生签名
        String sign = RSAUtil.sign(encodedData, privateKey);
        System.err.println("签名:" + sign);
        // 验证签名
        boolean status = RSAUtil.verify(encodedData, publicKey, sign);
        System.err.println("状态:" + status);
        assertTrue(status);
    }
}
