package com.lin.controller;

import com.alibaba.fastjson.JSON;
import com.lin.pojo.Project;
import com.lin.service.basicServiceImpl;
import com.lin.service.userServiceImpl;
import com.lin.util.tokenUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

/**
 * @author 林炳昌
 * @date 2023年02月21日 15:03
 */
@RestController
public class userController {

    @Autowired
    basicServiceImpl basicService;

    @Autowired
    userServiceImpl userService;

    @Autowired
    tokenUtil tokenUtil;

    @GetMapping("/home/projects")
    public String allProjects() {

        HashMap<String, Object> map = new HashMap<>();
        map.put("projectnames", basicService.queryProjectNameList());
        return JSON.toJSONString(map);

    }

    @GetMapping("/home/projects/{projectName}")
    public String oneProjects(@PathVariable String projectName, HttpServletResponse response) throws IOException {

        Project project = userService.oneProject(projectName);
        response.setContentType("multipart/form-data");
        String content = new String(Files.readAllBytes(Paths.get(project.getExplain())));
        project.setExplain(content);
        HashMap<String, Object> map = new HashMap<>();
        map.put("project",project);
        return JSON.toJSONString(map);

    }

    @PostMapping("/home/projects/{projectName}/{money}")
    public String contribute(@RequestHeader String Authorization, @PathVariable Integer money, @PathVariable String projectName) {

        HashMap<String, String> map = new HashMap<>();
        HashMap<String, Object> reduceParams = new HashMap<>();
        HashMap<String, Object> addParams = new HashMap<>();
        Claims claims = tokenUtil.parseToken(Authorization);
        if((Integer)claims.get("money") < money) {
            map.put("msg", "余额不足");
        } else {
            reduceParams.put("username", claims.get("username"));
            reduceParams.put("money", money);
            addParams.put("projectName", projectName);
            addParams.put("getMoney", money);
            userService.reduceMoney(reduceParams);
            userService.addProjectGetMoney(addParams);
            map.put("msg", "出资成功");
        }

        return JSON.toJSONString(map);

    }

    @PostMapping("/home/addMoney/{money}")
    public String addMoney(@RequestHeader String Authorization, @PathVariable String money) {

        HashMap<String, String> map = new HashMap<>();
        HashMap<String, Object> params = new HashMap<>();
        Claims claims = tokenUtil.parseToken(Authorization);
        params.put("username", claims.get("username"));
        params.put("money", money);
        userService.addMoney(params);
        map.put("msg", "充值成功");
        return JSON.toJSONString(map);

    }


}
