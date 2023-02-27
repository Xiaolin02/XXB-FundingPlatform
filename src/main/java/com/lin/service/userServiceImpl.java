package com.lin.service;

import com.lin.mapper.userMapper;
import com.lin.pojo.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 林炳昌
 * @date 2023年02月21日 15:39
 */

@Service
public class userServiceImpl implements userService{

    @Autowired
    userMapper userMapper;

    @Override
    public Project oneProject(String projectName) {
        return userMapper.oneProject(projectName);
    }

    @Override
    public void reduceMoney(Map<String, Object> params) {
        userMapper.reduceMoney(params);
    }

    @Override
    public void addProjectGetMoney(Map<String, Object> params) {
        userMapper.addProjectGetMoney(params);
    }

    @Override
    public void addMoney(Map<String, Object> params) {
        userMapper.addMoney(params);
    }

}
