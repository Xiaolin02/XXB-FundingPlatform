package com.lin.service;

import com.lin.mapper.basicMapper;
import com.lin.mapper.presenterMapper;
import com.lin.pojo.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 林炳昌
 * @date 2023年02月22日 17:22
 */
@Service
public class presenterServiceImpl implements presenterService{

    @Autowired
    presenterMapper presenterMapper;

    @Override
    public boolean release(Map<String, Object> params) {

        if(presenterMapper.queryProjectNameList().contains((String) params.get("projectName"))) {
            return false;
        } else {
            presenterMapper.release(params);
            return true;
        }

    }

    @Override
    public List<String> released(String username) {


        return presenterMapper.released(username);
    }

    @Override
    public List<String> review(String username) {
        return presenterMapper.review(username);
    }

    @Override
    public List<String> failed(String username) {
        return presenterMapper.failed(username);
    }

    @Override
    public Project check(Map<String, Object> params) {
        return presenterMapper.check(params);
    }


}
