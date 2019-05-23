package com.dd.service.impl;

import com.dd.model.UserInfo;
import com.dd.pojo.User;
import com.dd.prop.AuthProperties;
import com.dd.service.AuthService;
import com.dd.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthProperties authProperties;

    private static Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Override
    public Map<String, Object> authentication(String username, String password) {
        try {
            // 查询用户
            User user = null;
            if("zhangsan".equals(username) && "123".equals(password)) {
                user = new User();
                user.setUsername("zhangsan");
                user.setPassword("123");
                user.setId(1);
            }
            if (null == user) {
                logger.info("用户信息不存在，{}", username);
                return null;
            }

            // 用私钥生成token
            UserInfo userInfo = new UserInfo(user.getId(), user.getUsername());
            String token = JwtUtil.generateToken(
                    new UserInfo(user.getId(), user.getUsername()),
                    authProperties.getPrivateKey(), authProperties.getExpire());
            System.out.println("token:" + token);
            Map<String, Object> map = new HashMap<>();
            map.put("token", token);
            map.put("user", userInfo);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
