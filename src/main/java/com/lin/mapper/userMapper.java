package com.lin.mapper;

import com.lin.pojo.Project;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author 林炳昌
 * @date 2023年02月01日 14:11
 */

@Mapper
public interface userMapper {

    Project oneProject(String projectName);

    void reduceMoney(Map<String, Object> params);

    void addProjectGetMoney(Map<String, Object> params);

    void addMoney(Map<String, Object> params);

}
