package com.dd.newUtil;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * 用公钥加密，私钥解密
 */
public class TestDecodeByPrikey {
    public static void main(String[] args) throws Exception {
        //生成公私钥
        KeyPairGenerator keyPairGenerator = KeyPairGenerator
                .getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey pubKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privKey = (RSAPrivateKey) keyPair.getPrivate();

        String input = "thisIsMyPassword$7788";
        Cipher cipher = Cipher.getInstance("RSA");
        //公钥加密
        cipher.init(Cipher.ENCRYPT_MODE, privKey);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        //加密后的东西
        System.out.println("cipher: " + new String(cipherText));
        //私钥解密
        cipher.init(Cipher.DECRYPT_MODE, pubKey);
        byte[] plainText = cipher.doFinal(cipherText);
        System.out.println("plain : " + new String(plainText));

        //总结，可以私钥加密，公钥解密；也可以公钥加密，私钥解密
        //签名可以用私钥加密，公钥解密
        //前端可以用公钥解密，后台私钥解密
    }
}
