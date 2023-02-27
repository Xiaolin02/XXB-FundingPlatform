package com.lin.service;

import com.lin.mapper.basicMapper;
import com.lin.pojo.Admin;
import com.lin.pojo.Project;
import com.lin.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 林炳昌
 * @date 2023年02月19日 22:09
 */
@Service
public class basicServiceImpl implements basicService{

    @Autowired
    basicMapper mapper;

    @Override
    public boolean isExisted(String username) {

        List<String> list = mapper.queryUsernameList();
        return list.contains(username);

    }

    @Override
    public void register(User user) {

        mapper.addUser(user);

    }

    @Override
    public boolean isPswTrue(String username, Integer password) {

        User user = mapper.queryUser(username);
        return user.getPassword().equals(password);

    }

    @Override
    public void certify(String username) {

        mapper.certify(username);

    }

    @Override
    public List<String> search(String key) {

        return mapper.search(key);

    }

    @Override
    public boolean adminSignIn(String adminName, Integer password) {
        List<String> list = mapper.queryAdminList();
        if(!list.contains(adminName))
            return false;
        else {
            Admin admin = mapper.queryAdmin(adminName);
            return admin.getPassword().equals(password);
        }
    }

    @Override
    public List<String> queryProjectNameList() {
        return mapper.queryProjectNameList();
    }

    @Override
    public User queryUser(String username) {
        return mapper.queryUser(username);
    }

}
