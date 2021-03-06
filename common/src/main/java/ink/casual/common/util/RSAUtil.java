package ink.casual.common.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lwt
 * @date 2019/4/13 10:12
 */
public class RSAUtil {

    public static final String KEY_ALGORITHM = "RSA";

    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    public static final String PUBLIC_KEY = "RSAPublicKey";

    public static final String PRIVATE_KEY = "RSAPrivateKey";

    public static byte[] decryptBASE64(String key) {
        return Base64.decodeBase64(key);
    }

    public static String encryptBASE64(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    /**
     * 用私钥对信息生成数字签名
     *
     * @param data       加密数据
     * @param privateKey 私钥
     * @return
     */
    public static String sign(byte[] data, String privateKey){
        String s;
        try {
            // 解密由base64编码的私钥
            byte[] keyBytes = decryptBASE64(privateKey);
            // 构造PKCS8EncodedKeySpec对象
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            // KEY_ALGORITHM 指定的加密算法
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            // 取私钥匙对象
            PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
            // 用私钥对信息生成数字签名
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(priKey);
            signature.update(data);
            s = encryptBASE64(signature.sign());
        }catch (Exception e){
            throw new RuntimeException(e.getLocalizedMessage());
        }
        return s;
    }

    /**
     * 校验数字签名
     *
     * @param data      加密数据
     * @param publicKey 公钥
     * @param sign      数字签名
     * @return 校验成功返回true 失败返回false
     */
    public static boolean verify(byte[] data, String publicKey, String sign){
        // 解密由base64编码的公钥
        byte[] keyBytes = decryptBASE64(publicKey);
        // 构造X509EncodedKeySpec对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        // KEY_ALGORITHM 指定的加密算法
        boolean verify;
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            // 取公钥匙对象
            PublicKey pubKey = keyFactory.generatePublic(keySpec);
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(pubKey);
            signature.update(data);
            // 验证签名是否正常
            verify = signature.verify(decryptBASE64(sign));
        }catch (Exception e){
            throw new RuntimeException(e.getLocalizedMessage());
        }
        return verify;
    }

    public static byte[] decryptByPrivateKey(byte[] data, String key){
        byte[] bytes;
        try {
            // 对密钥解密
            byte[] keyBytes = decryptBASE64(key);
            // 取得私钥
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
            // 对数据解密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            bytes = cipher.doFinal(data);
        }catch (Exception e){
            throw new RuntimeException(e.getLocalizedMessage());
        }
        return bytes;
    }

    /**
     * 解密<br>
     * 用私钥解密
     *
     * @param data
     * @param key
     * @return
     */
    public static byte[] decryptByPrivateKey(String data, String key){
        return decryptByPrivateKey(decryptBASE64(data),key);
    }

    /**
     * 解密<br>
     * 用公钥解密
     *
     * @param data
     * @param key
     * @return
     */
    public static byte[] decryptByPublicKey(byte[] data, String key){
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(key);
        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        byte[] bytes;
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            Key publicKey = keyFactory.generatePublic(x509KeySpec);
            // 对数据解密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            bytes = cipher.doFinal(data);
        }catch (Exception e){
            throw new RuntimeException(e.getLocalizedMessage());
        }
        return bytes;
    }

    /**
     * 加密<br>
     * 用公钥加密
     *
     * @param data
     * @param key
     * @return
     */
    public static byte[] encryptByPublicKey(String data, String key){
        byte[] bytes;
        try {
            // 对公钥解密
            byte[] keyBytes = decryptBASE64(key);
            // 取得公钥
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            Key publicKey = keyFactory.generatePublic(x509KeySpec);
            // 对数据加密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            bytes = cipher.doFinal(data.getBytes(Charset.forName("UTF-8")));
        }catch (Exception e){
            throw new RuntimeException(e.getLocalizedMessage());
        }
        return bytes;
    }

    /**
     * 加密<br>
     * 用私钥加密
     *
     * @param data
     * @param key
     * @return
     */
    public static byte[] encryptByPrivateKey(byte[] data, String key){
        byte[] bytes;
        try {
            // 对密钥解密
            byte[] keyBytes = decryptBASE64(key);
            // 取得私钥
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
            // 对数据加密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            bytes = cipher.doFinal(data);
        }catch (Exception e){
            throw new RuntimeException(e.getLocalizedMessage());
        }
        return bytes;
    }

    /**
     * 取得私钥
     *
     * @param keyMap
     * @return
     */
    public static String getPrivateKey(Map<String, Key> keyMap){
        Key key = keyMap.get(PRIVATE_KEY);
        return encryptBASE64(key.getEncoded());
    }

    /**
     * 取得公钥
     *
     * @param keyMap
     * @return
     */
    public static String getPublicKey(Map<String, Key> keyMap){
        Key key = keyMap.get(PUBLIC_KEY);
        return encryptBASE64(key.getEncoded());
    }

    /**
     * 初始化密钥
     *
     * @return
     */
    public static Map<String, Key> initKey(){
        KeyPairGenerator keyPairGen = null;
        try {
            keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        }catch (Exception e){
            throw new RuntimeException(e.getLocalizedMessage());
        }
        keyPairGen.initialize(512);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        Map<String, Key> keyMap = new HashMap<>(2);
        // 公钥
        keyMap.put(PUBLIC_KEY, keyPair.getPublic());
        // 私钥
        keyMap.put(PRIVATE_KEY, keyPair.getPrivate());
        return keyMap;
    }
}
