package com.caopeng.travel;

import com.caopeng.travel.mapper.ProjectMapper;
import com.caopeng.travel.mapper.UserMapper;
import com.caopeng.travel.mapper.UserProjectMapper;
import com.caopeng.travel.pojo.Project;
import com.caopeng.travel.pojo.User;
import com.caopeng.travel.pojo.UserProject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class TravelApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    UserProjectMapper userProjectMapper;

    @Test
    void contextLoads() {
        List<Project> AllProjects = projectMapper.getMyAllProject(10);
        for (Project allProject : AllProjects) {
            System.out.println(allProject);
        }
    }

}
