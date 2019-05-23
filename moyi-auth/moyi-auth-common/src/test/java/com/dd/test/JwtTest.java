package com.dd.test;

import com.dd.model.UserInfo;
import com.dd.util.JwtUtil;
import com.dd.util.RsaUtil;
import org.junit.Before;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;

public class JwtTest {

    private static final String publicKeyPath = "e:/rsa.pub";

    private static final String privateKeyPath = "e:/rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    //测试生成公钥和私钥
    @Test
    public void testRsa() throws Exception {
        RsaUtil.generateKey(publicKeyPath, privateKeyPath, "234");
    }

    //获取磁盘里的公钥私钥
    @Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtil.getPublicKey(publicKeyPath);
        this.privateKey = RsaUtil.getPrivateKey(privateKeyPath);
    }

    //生成token
    @Test
    public void testGenerateToken() throws Exception {
        String token = JwtUtil.generateToken(new UserInfo(20L, "jack"), privateKey, 5);
        System.out.println("token = " + token);
        //eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MjAsInVzZXJuYW1lIjoiamFjayIsImV4cCI6MTU1NjM0ODUzNn0.A7nt-MSs_VPDHUl95SBj8qPA2P4n0ftK53xIoLmv8op1-yepXLDDoia1mK710iAZ4V5FY8O6_5JFG2uNjX9RYykUsDPqmkOaTLHvPqtuGq0GO7DJ8Gjg_3P_d7xnEF30rbrejAYxMtPQ17ob4EmSmaDoCiXVA89bv47XsmlX3wE
    }

    //用token解析成用户信息
    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MjAsInVzZXJuYW1lIjoiamFjayIsImV4cCI6MTU1NjM0ODUzNn0.A7nt-MSs_VPDHUl95SBj8qPA2P4n0ftK53xIoLmv8op1-yepXLDDoia1mK710iAZ4V5FY8O6_5JFG2uNjX9RYykUsDPqmkOaTLHvPqtuGq0GO7DJ8Gjg_3P_d7xnEF30rbrejAYxMtPQ17ob4EmSmaDoCiXVA89bv47XsmlX3wE";
        UserInfo user = JwtUtil.getInfoFromToken(token, publicKey);
        System.out.println("id: " + user.getId());
        System.out.println("userName: " + user.getUsername());
    }
}
