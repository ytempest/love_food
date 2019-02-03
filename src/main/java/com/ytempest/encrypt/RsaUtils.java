package com.ytempest.encrypt;


import com.ytempest.encrypt.binary.Base64;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * @author ytempest
 * @date 2019/2/1
 */
class RsaUtils {

    private static final String PRIVATE_KEY = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAlsBOizDjX4WsDYK2GZBsFLKzBis6CI3C0zuOU/Rwn+qh/a0srmw6uHjdYFnxoPz8h5zSOxPSBaLZ5ltz0ovU2wIDAQABAkA+3Qij0IowbiyWIbjQJkJ7yx0OaH41zO++aCps5hUFCg30aOuo/9EAn95p4z6aCBDRuDiTrXpkN6BIh+tQwOTpAiEAzG3E/VTnF7gg2TarqyANyqHS66/CTwDIoOm8rUqOqRcCIQC8x/skz+s0Buu/ijuuH2BrWXmVDJ/oCghLvR38Q+yE3QIgUolPK2kIFI+G06w7C1BZwSIs4nOH5BXQ6wbz4pNjnekCIQC8nTYArrg58BxE8FD2hKTqPKhsUgPGa5ekTJyT+i1rBQIgDI9bZcKBLjhk48jY7aGmAOKUSpuezSnE5R9Xev/c4EM=";

    private static final String ALGORITHM_RSA = "RSA";


    /**
     * 私钥解密
     */
    public static String decryptionByPrivateKey(String target) throws Exception {
        PrivateKey privateKey = getPrivateKey(PRIVATE_KEY);
        Cipher cipher = Cipher.getInstance(ALGORITHM_RSA);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        cipher.update(decodeBase64(target));
        return new String(cipher.doFinal(), "UTF-8");
    }


    /**
     * 获取私钥
     *
     * @param privateKeyStr
     */
    public static PrivateKey getPrivateKey(String privateKeyStr) throws Exception {
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(decodeBase64(privateKeyStr));
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
        return keyFactory.generatePrivate(privateKeySpec);
    }


    /**
     * Base64编码
     */
    public static String encodeBase64(byte[] source) throws Exception {
        return Base64.encodeBase64URLSafeString(source);
    }

    /**
     * Base64解码
     */
    public static byte[] decodeBase64(String target) throws Exception {
        return Base64.decodeBase64(target);
    }

}