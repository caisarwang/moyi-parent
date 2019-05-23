package com.dd.newUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * 使用字符串形式密钥加密解密
 */
public class TestTokenByStringKey {
    public static void main(String[] args) {
        String key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIebRKWrUTlmMXWLXeA7Fzv5saka" +
                "Dj4V237/uC04r2SJt+gE+rQjxsa8oo+aSJbhHVSxLFQwBiLHvqoYGo4qfgcMxRDrrsYNs++if1lP" +
                "Kg2v3uvynzZMfPKe0HSycvPRjnmYaEt5QEAi2ct6p8eF9QFaZSsEYaQL1erGx9QWRgp5AgMBAAEC" +
                "gYBoXci0lKibu5ofNFbYx8G3CBKC6inCAMKA+MDAFRtwDko7SPHGiK5osC+QxBZQAQu47e8HEDr4" +
                "s7B7aretaOi61Jxnee3W2KyvlUQQ4eQDMcpQ+Zdau72lcj/fyLI4ydZ9LcdqR9y9U6A7EkrSlgak" +
                "YvOIfebZ9V+HqBvO2jUTgQJBANap/Y00Tr0f9eVnHCYLnmF0KjNKo+8pZ4dgZBEdJn8Vj0IeNW9G" +
                "40DlzfeC7KwkpfJkOVGnCvT5tdyAgSyRSSkCQQChuBV5QoaW1vW5Zsu4QHhh19wQYigzfh7G9HUk" +
                "K3XU2pT6HIIjqSeb5ilHuz7si9JChaEJfzDSzRjTDl4VeNDRAkAJWSHHkm2TYt7sfI8HQR5GlC56" +
                "SVfWRHFTvejdre1NP30bngEptWaUHu3XR4ZTAM2RPrgYTDP38x/uWFJHxJPxAkB4mBRzS1egDgZJ" +
                "Tk2mRXU/yT9SfE/sLiV3SX49Daedpa4oYK/vNcnSqKyp0AjZTl7pukpfEZjv6X+Ui4c/TcuxAkEA" +
                "mIoPn5+ih6Sts/4i3gSRWwT4kXnslI0MXdI/0kz1jd2OyfIfQRYDpLQ0/c2fbiyEWk0XPpPEcOc3" +
                "vuWGGaZ92A==";
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date now = new Date(System.currentTimeMillis());
        JwtBuilder builder = Jwts.builder()
                .setHeaderParam("typ", "JWT")    //设置header
                .setHeaderParam("alg", "HS256")
                .setIssuedAt(now)     //设置iat
                .claim("name", "liuwillow")   //设置payload的键值对
                .claim("level", "100")
                .setIssuer("lwl")
                .signWith(signatureAlgorithm, key);    //签名，需要算法和key
        //这里如果用的是字符串形式的prikey，那么在解密的时候也要使用同样的prikey，而不能用pubkey，如果用Key对象形式的prikey，那么解密的时候可以用对象Key形式的prikey和pubkey进行解密
        String token = builder.compact();
        System.out.println("生成的token:" + token);

        //获取claims
        Claims claims = Jwts.parser()
                .setSigningKey(key)     //此处的key要与之前创建的key一致
                .parseClaimsJws(token)
                .getBody();
        //获取name和level
        String name = (String) claims.get("name");
        String level = (String) claims.get("level");
        System.out.println("name:" + name + " level:" + level);
    }

}
