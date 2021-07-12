package com.caopeng.travel.mapper;


import com.caopeng.travel.pojo.User;
import com.caopeng.travel.pojo.UserProject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserProjectMapper {

    public int saveUserProject(UserProject userProject);

    public int deleteUserProject(Integer userId, Integer projectId);

    List<Integer> getUserIds(Integer projectId);
}
