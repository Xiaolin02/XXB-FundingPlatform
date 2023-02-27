package com.lin.service;

import com.lin.pojo.Project;

import java.util.Map;

/**
 * @author 林炳昌
 * @date 2023年02月21日 15:38
 */
public interface userService {

    Project oneProject(String projectName);

    void reduceMoney(Map<String, Object> params);

    void addProjectGetMoney(Map<String, Object> params);

    void addMoney(Map<String, Object> params);

}
