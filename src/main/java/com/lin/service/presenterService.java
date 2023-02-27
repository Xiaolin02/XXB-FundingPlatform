package com.lin.service;

import com.lin.pojo.Project;

import java.util.List;
import java.util.Map;

/**
 * @author 林炳昌
 * @date 2023年02月22日 17:21
 */
public interface presenterService {

    boolean release(Map<String,Object> params);

    List<String> released(String username);

    List<String> review(String username);

    List<String> failed(String username);

    Project check(Map<String, Object> params);

}
