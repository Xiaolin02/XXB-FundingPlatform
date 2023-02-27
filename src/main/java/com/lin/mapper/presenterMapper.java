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
public interface presenterMapper {

    void release(Map<String, Object> params);

    List<String> released(String username);

    List<String> review(String username);

    List<String> failed(String username);

    Project check(Map<String,Object> params);

    List<String> queryProjectNameList();

}
