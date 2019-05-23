package com.dd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("category")
public class CategoryController {

    @RequestMapping("getCategory")
    @ResponseBody
    public Map getCategory() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "手机");
        map.put("price", "2000");
        return map;
    }

    @RequestMapping("index")
    public String index(ModelMap map) {
        map.put("testname", "aaa");
        return "/index";
    }
}
