package com.lin.controller;

import com.alibaba.fastjson.JSON;
import com.lin.pojo.Project;
import com.lin.service.presenterServiceImpl;
import com.lin.util.tokenUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @author 林炳昌
 * @date 2023年01月26日 11:01
 */
@RestController
public class presenterController {

    @Autowired
    presenterServiceImpl presenterService;

    @Autowired
    tokenUtil tokenUtil;

    @Value("${filePath}")
    String filePath;

    @PostMapping("/home/release/{projectName}/{money}")
    public String release(@RequestPart MultipartFile explain, @RequestHeader String Authorization, @PathVariable Integer money, @PathVariable String projectName, HttpServletResponse response) throws IOException {

        Claims claims = tokenUtil.parseToken(Authorization);
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, Object> params = new HashMap<>();
        if(claims.get("isCertified").equals(false)) {
            response.setStatus(403);
            map.put("msg", "尚未进行身份认证");
        } else {
            File directoryFile = new File(filePath);
            if(!directoryFile.exists())
                directoryFile.mkdir();
            String name = explain.getOriginalFilename();
            explain.transferTo(new File(filePath + name));
            params.put("projectName", projectName);
            params.put("money", money);
            params.put("explain", filePath + name);
            params.put("userName", claims.get("username"));
            if(presenterService.release(params)) {
                map.put("msg", "发布成功!请耐心等待审核...");
            } else {
                response.setStatus(403);
                map.put("msg", "发布失败!项目名称重复");
            }
        }

        return JSON.toJSONString(map);

    }

    @GetMapping("/home/myProject/released")
    public String released(@RequestHeader String Authorization) {

        HashMap<String, Object> map = new HashMap<>();
        Claims claims = tokenUtil.parseToken(Authorization);
        map.put("projectnames", presenterService.released((String) claims.get("username")));
        return JSON.toJSONString(map);

    }

    @GetMapping("/home/myProject/review")
    public String review(@RequestHeader String Authorization) {

        HashMap<String, Object> map = new HashMap<>();
        Claims claims = tokenUtil.parseToken(Authorization);
        map.put("projectnames", presenterService.review((String) claims.get("username")));
        return JSON.toJSONString(map);

    }

    @GetMapping("/home/myProject/failed")
    public String failed(@RequestHeader String Authorization) {

        HashMap<String, Object> map = new HashMap<>();
        Claims claims = tokenUtil.parseToken(Authorization);
        map.put("projectnames", presenterService.failed((String) claims.get("username")));
        return JSON.toJSONString(map);

    }

    @GetMapping("/home/myProject/{projectName}")
    public String check(@RequestHeader String Authorization, @PathVariable String projectName) {

        HashMap<String, Project> map = new HashMap<>();
        HashMap<String, Object> params = new HashMap<>();
        Claims claims = tokenUtil.parseToken(Authorization);
        params.put("username", claims.get("username"));
        params.put("projectName", projectName);
        map.put("project", presenterService.check(params));
        return JSON.toJSONString(map);

    }

}
