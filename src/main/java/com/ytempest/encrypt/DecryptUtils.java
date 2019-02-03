package com.ytempest.encrypt;


/**
 * @author ytempest
 * @date 2019/2/1
 */
public class DecryptUtils {

    /**
     * 解密
     */
    public static String decrypt(String source) {
        try {
            String decrypt = DesUtils.decrypt(source);
            return RsaUtils.decryptionByPrivateKey(decrypt);
        } catch (Exception e) {
            throw new IllegalStateException("无法对数据进行解密");
        }
    }
}