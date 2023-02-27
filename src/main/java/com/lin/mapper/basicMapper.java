package com.lin.mapper;

import com.lin.pojo.Admin;
import com.lin.pojo.Project;
import com.lin.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 林炳昌
 * @date 2023年02月01日 14:12
 */
@Mapper
public interface basicMapper {

    List<String> queryUsernameList();

    void addUser(User user);

    User queryUser(String username);

    void certify(String username);

    List<String> search(String key);

    List<String> queryAdminList();

    Admin queryAdmin(String adminName);

    List<String> queryProjectNameList();

}
