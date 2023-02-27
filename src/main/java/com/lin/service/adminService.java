package com.lin.service;

import com.lin.pojo.Project;

import java.util.Map;

/**
 * @author 林炳昌
 * @date 2023年02月22日 21:59
 */
public interface adminService {

    Map<String, Object> check(String projectName);

    void setStatus(Map<String, String> params);

}
