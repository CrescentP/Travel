package com.caopeng.travel.mapper;

import com.caopeng.travel.pojo.Project;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Crescent_P
 * @date 2021-06-09 10:17
 */
@Mapper
@Repository
public interface ProjectMapper {

    /**
     * 保存项目
     * @author Crescent_P
     * @date 2021-06-10 15:35:31
     * @param project
     * @return
     **/
    public int saveProject(Project project);

    /**
     * 得到项目
     * @author Crescent_P
     * @date 2021-06-10 15:35:41
     * @param project
     * @return
     **/
    public Project getProject(Project project);

    /**
     * 得到自己创建的项目
     * @author Crescent_P
     * @date 2021-06-10 15:35:51
     * @param userId
     * @return
     **/
    public List<Project> getMyProjects(Integer userId);

    /**
     * 通过id获得项目
     * @author Crescent_P
     * @date 2021-06-10 15:36:03
     * @param id
     * @return
     **/
    public Project  getProjectById(Integer id);


    /**
     * 通过id删除项目
     * @author Crescent_P
     * @date 2021-06-10 15:36:35
     * @param id
     * @return
     **/
    public int deleteProjectById(Integer id);

    /**
     * 更新项目
     * @author Crescent_P
     * @date 2021-06-10 18:34:58
     * @param project
     * @return
     **/
    public int updateProject(Project project);

    /**
     *
     * @author 得到所有的项目
     * @date 2021-06-10 19:32:51
     * @return
     **/
    public List<Project> getAllProject();

    /**
     * 得到我参加的所有项目
     * @author Crescent_P
     * @date 2021-06-10 19:32:56
     * @return
     **/
    List<Project> getMyAllProject(Integer id);

    /**
     * 根据模糊查询获取到项目
     * @author Crescent_P
     * @date 2021-06-13 13:42:45
     * @param projectName
     * @return
     **/
    List<Project> getProjectByName(String projectName);
}
