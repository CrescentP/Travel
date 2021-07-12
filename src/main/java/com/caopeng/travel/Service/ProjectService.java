package com.caopeng.travel.Service;

import com.caopeng.travel.mapper.ProjectMapper;
import com.caopeng.travel.pojo.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Crescent_P
 * @date 2021-06-09 10:37
 */

@Service
public class ProjectService {
    @Autowired
    private ProjectMapper projectMapper;

    public Integer saveProject(Project project){
        return projectMapper.saveProject(project);
    }

    public Project getProject(Project project){
        return projectMapper.getProject(project);
    }

    public List<Project> getMyProjects(Integer userId){
        return projectMapper.getMyProjects(userId);
    }

    public Project getProjectById(Integer id){return projectMapper.getProjectById(id);}

    public int deleteProject(Integer id){return projectMapper.deleteProjectById(id);}

    public int updateProject(Project project){return projectMapper.updateProject(project);}

    public List<Project> getAllProjects(){return projectMapper.getAllProject();}

    public List<Project> getMyAllProject(Integer userId){ return projectMapper.getMyAllProject(userId); }

    public List<Project> getProjectsByName(String projectName) {return projectMapper.getProjectByName(projectName);}

}
