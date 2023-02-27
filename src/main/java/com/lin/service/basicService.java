package com.lin.service;

import com.lin.pojo.Project;
import com.lin.pojo.User;

import java.util.List;

/**
 * @author 林炳昌
 * @date 2023年02月13日 11:54
 */
public interface basicService {

    boolean isExisted (String username);

    void register (User user);

    boolean isPswTrue(String username, Integer password);

    void certify(String username);

    List<String> search(String key);

    boolean adminSignIn(String adminName, Integer password);

    List<String> queryProjectNameList();

    User queryUser(String username);

}
