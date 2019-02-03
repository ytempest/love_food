package com.ytempest.util;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class ClientEncryptUtils {


    public static void main(String[] args) {
        String str = "待签名数据fhefejk4324hJfasf&()*()&";
        String encrypt = encrypt(str);
        System.out.println(" deprecatedEncode = " + encrypt);
        boolean result = validate(str, encrypt);
        System.out.println(" result = " + result);


    }


    private static final String SIGN_ALGORITHMS = "SHA1withRSA";

    private static final String privateKey = "MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEAlEFnTajTCeb4+2o+FVQnhaE4HI/W4+XxI9h/98Y3NBMnc9h/HehCdIJ/Mis4bMyoHjezg1ezoI/CLFDZD4kEzwIDAQABAkBX+AL7AqzOmF2umNjjwP5+fS4VTIg+zAbstR8hf1zsKXYxMpyi4qIkO+p9TeTg4shPRkisv4zQCvkMsawhU8cxAiEAxAmtsXY7HlEAV+r5b0h3VTTG39SwJGk7gQTPbK7BxzkCIQDBmjt1ug+1/WeUNw58SpcidZYJC5Z3P9zBjgb22TzkRwIgJ6YpejYGePg7EI0Wy/olIJefDZBP291oyox1g27JBIkCIGwXHkd2YDsthdz8eDvwsTzuBZx7OcDOiQQPtdewJg0VAiBb/QgBc6umm8gYzcfpeYriuGSeXoxydiBQ/rdJdqUkLw==";
    private static final String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJRBZ02o0wnm+PtqPhVUJ4WhOByP1uPl8SPYf/fGNzQTJ3PYfx3oQnSCfzIrOGzMqB43s4NXs6CPwixQ2Q+JBM8CAwEAAQ==";

    /**
     * RSA签名
     */
    public static String encrypt(String content) {
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decode(privateKey));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey priKey = keyFactory.generatePrivate(keySpec);
            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
            signature.initSign(priKey);
            signature.update(content.getBytes("UTF-8"));
            byte[] signed = signature.sign();
            return Base64.encode(signed);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * RSA验签名检查
     */
    public static boolean validate(String content, String sign) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64.decode(publicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

            Signature signature = Signature
                    .getInstance(SIGN_ALGORITHMS);

            signature.initVerify(pubKey);
            signature.update(content.getBytes("UTF-8"));

            return signature.verify(Base64.decode(sign));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


}
