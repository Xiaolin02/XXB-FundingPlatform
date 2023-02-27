package com.lin.service;

import com.lin.mapper.adminMapper;
import com.lin.pojo.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 林炳昌
 * @date 2023年02月22日 21:59
 */
@Service
public class adminServiceImpl implements adminService{

    @Autowired
    adminMapper adminMapper;

    @Override
    public Map<String, Object> check(String projectName) {

        return adminMapper.check(projectName);

    }

    @Override
    public void setStatus(Map<String, String> params) {

        Map<String, Object> map = adminMapper.check(params.get("projectName"));
        adminMapper.addProject(new Project(
                (String) map.get("userName"),
                params.get("projectName"),
                (Integer) map.get("money"),
                (String) map.get("explain"),
                params.get("status"),
                0));
        adminMapper.deleteProject(params.get("projectName"));

    }
}
