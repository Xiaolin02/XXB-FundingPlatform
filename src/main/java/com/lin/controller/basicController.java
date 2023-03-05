package com.lin.controller;

import com.alibaba.fastjson.JSON;
import com.lin.pojo.User;
import com.lin.service.basicServiceImpl;
import com.lin.util.tokenUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author 林炳昌
 * @date 2023年01月24日 21:13
 */
@RestController
public class basicController {

    @Autowired
    basicServiceImpl service;

    @Autowired
    tokenUtil tokenUtil;

    @GetMapping("/index")
    public void index() {

    }

    @PostMapping("/index/register/{username}/{password}")
    public String register(@PathVariable String username, @PathVariable Integer password, HttpServletResponse response, Model model) {

        HashMap<String, String> map = new HashMap<>();
        if (service.isExisted(username)) {
            response.setStatus(403);
            map.put("msg", "用户名已经存在");
        } else {
            service.register(new User(username, password, 0, false));
            map.put("msg", "注册成功");
        }
        return JSON.toJSONString(map);

    }

    @GetMapping("/home/{username}/{password}")
    public String signIn(@PathVariable String username, @PathVariable Integer password, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HashMap<String, String> map = new HashMap<>();
        if (!service.isExisted(username)) {

            response.setStatus(403);
            map.put("msg", "用户不存在");
            return JSON.toJSONString(map);

        } else if (!service.isPswTrue(username, password)) {

            response.setStatus(403);
            map.put("msg", "密码错误");
            return JSON.toJSONString(map);

        } else {
            map.put("Authorization", tokenUtil.getToken(service.queryUser(username)));
            map.put("msg", "登陆成功");
            return JSON.toJSONString(map);
        }

    }

    @PostMapping("/home/setting/{id}/{tel}")
    public String setting(@RequestHeader String Authorization,@PathVariable String id, @PathVariable String tel) {

        HashMap<String, String> map = new HashMap<>();
        String REGEX_ID_CARD = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
                "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
        String REGEX_TEL = "^1[3-9]\\d{9}$";
        Pattern P_ID_CARD = Pattern.compile(REGEX_ID_CARD);
        Pattern P_TEL = Pattern.compile(REGEX_TEL);
        if (id.isEmpty() || !P_ID_CARD.matcher(id).matches()) {
            map.put("msg", "身份证号错误");
            return JSON.toJSONString(map);
        } else if (tel.isEmpty() || !P_TEL.matcher(tel).matches()) {
            map.put("msg", "手机号错误");
            return JSON.toJSONString(map);
        } else {
            service.certify((String) tokenUtil.parseToken(Authorization).get("username"));
            map.put("msg", "修改成功");
            return JSON.toJSONString(map);
        }


    }

    @GetMapping("/home/search/{key}")
    public String search(@PathVariable String key, HttpServletResponse response) {

        HashMap<String, Object> map = new HashMap<>();
        List<String> projects = service.search(key);
        if (projects.isEmpty()) {
            map.put("msg", "内容为空");
            response.setStatus(403);
        } else {
            map.put("projectnames", projects);
        }
        return JSON.toJSONString(map);

    }

    @GetMapping("/admin/{adminName}/{password}")
    public String adminSignIn(@PathVariable String adminName, @PathVariable Integer password, HttpServletResponse response) {

        HashMap<String, Object> map = new HashMap<>();
        if(service.adminSignIn(adminName,password)) {
            map.put("msg", "登陆成功");
            map.put("projectnames", service.queryProjectNameList());
        } else {
            response.setStatus(403);
            map.put("msg", "登陆失败");
        }
        return JSON.toJSONString(map);

    }

}
