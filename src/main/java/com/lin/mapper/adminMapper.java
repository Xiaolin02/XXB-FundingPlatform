package com.lin.mapper;

import com.lin.pojo.Project;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author 林炳昌
 * @date 2023年02月01日 14:12
 */
@Mapper
public interface adminMapper {

    Map<String, Object> check(String projectName);

    void deleteProject(String projectName);

    void addProject(Project project);

}
