package com.dd.controller;

import com.dd.model.UserInfo;
import com.dd.prop.AuthProperties;
import com.dd.service.AuthService;
import com.dd.util.CookieUtils;
import com.dd.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthProperties authProperties;

    /**
     * 登录授权
     *
     * @return
     */
    @PostMapping("login")
    public Object authentication(UserDTO user,
                                 HttpServletRequest request, HttpServletResponse response) {
        // 登录校验
        Map<String, Object> map = authService.authentication("zhangsan", "123");
        if (map == null) {
            return "用户名或密码错误";
        }
        if(map.get("token") == null) {
            return "用户名或密码错误";
        }
        // 将token写入cookie,并指定httpOnly为true，防止通过JS获取和修改
        CookieUtils.setCookie(request, response, "moyitokenname", String.valueOf(map.get("token")),
                3600, true);
        map.put("msg", "登录成功");
        return map;
    }

    @RequestMapping("getCook")
    public Object getCook(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        System.out.println(cookies);
        return "success";
    }

    /**
     * 验证用户是否登录
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("verfy")
    public Object verfy(@CookieValue("moyitokenname") String token, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(StringUtils.isNoneEmpty(token)) {
            UserInfo info = JwtUtil.getInfoFromToken(token, authProperties.getPublicKey());
            if(info == null) {
                return "need login";
            }
        }
        return "success";
    }
}
