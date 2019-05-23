package com.dd.newUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;

import java.security.*;
import java.text.SimpleDateFormat;

/**
 * 使用jjwt私钥签名，公钥解密的方式。应用的公私钥都是对象的方式
 */
public class TestTokenByObjectKey {

    private static PublicKey publicKey;
    private static PrivateKey privateKey;

    /**
     * 一个JWT实际上就是一个字符串，它由三部分组成，头部(Header)、载荷(Payload)与签名(Signature)
     * @param id 当前用户ID
     * @param issuer 该JWT的签发者，是否使用是可选的
     * @param subject 该JWT所面向的用户，是否使用是可选的
     * @param ttlMillis 什么时候过期，这里是一个Unix时间戳，是否使用是可选的
     * @param audience 接收该JWT的一方，是否使用是可选的
     * @return
     */
    public static String createToken() {
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        publicKey = keyPair.getPublic();
        privateKey = keyPair.getPrivate();

        String token = Jwts.builder()
            .claim("userId", "1")
            .setExpiration(DateTime.now().plusSeconds(600).toDate())
            .signWith(SignatureAlgorithm.RS256, privateKey)
            .compact();
        return token;

    }

    //这里可以公钥解密，也可以私钥解密
    public static Claims getClaimsByPublicKey(String token) {
        Claims claims = Jwts.parser().setSigningKey(privateKey).parseClaimsJws(token).getBody();
        return claims;
    }

    public static void main(String[] args) {
        String token = TestTokenByObjectKey.createToken();
        System.out.println(token);
        //解密token信息
        Claims claims = TestTokenByObjectKey.getClaimsByPublicKey(token);
        System.out.println("---------------------------解密的token信息----------------------------------");
        System.out.println("userId: " + claims.get("userId"));
        System.out.println("Expiration: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getExpiration()));
    }

}