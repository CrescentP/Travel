package com.caopeng.travel.Service;

import com.caopeng.travel.mapper.UserProjectMapper;
import com.caopeng.travel.pojo.User;
import com.caopeng.travel.pojo.UserProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Crescent_P
 * @date 2021-06-10 8:38
 */
@Service
public class UserProjectService {

    @Autowired
    UserProjectMapper userProjectMapper;

    public int saveUserProjectMapper(UserProject userProject){
        return userProjectMapper.saveUserProject(userProject);
    }

    public int deleteUserProject(Integer userId, Integer projectId){return userProjectMapper.deleteUserProject(userId,projectId);}

    public List<Integer> getUserIds(Integer projectId) {return userProjectMapper.getUserIds(projectId);}
}
