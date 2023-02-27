package com.lin.controller;

import com.alibaba.fastjson2.JSON;
import com.lin.service.adminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author 林炳昌
 * @date 2023年02月22日 21:41
 */
@RestController
public class adminController {

    @Autowired
    adminServiceImpl adminService;

    @GetMapping("/admin/{projectName}")
    public String check(@PathVariable String projectName) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("project", adminService.check(projectName));
        return JSON.toJSONString(map);

    }

    @PostMapping("/admin/{projectName}/{status}")
    public String setStatus(@PathVariable String projectName, @PathVariable String status) {

        HashMap<String, Object> map = new HashMap<>();
        HashMap<String, String> params = new HashMap<>();
        params.put("projectName", projectName);
        params.put("status", status);
        adminService.setStatus(params);
        map.put("msg", "审核成功");
        return JSON.toJSONString(map);

    }


}
