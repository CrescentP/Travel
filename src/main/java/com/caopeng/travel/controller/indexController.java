package com.caopeng.travel.controller;

import com.caopeng.travel.Service.ProjectService;
import com.caopeng.travel.Service.UserProjectService;
import com.caopeng.travel.Service.UserService;
import com.caopeng.travel.pojo.Project;
import com.caopeng.travel.pojo.User;
import com.caopeng.travel.pojo.UserProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Crescent_P
 * @date 2021-06-07 19:44
 */

@Controller
public class indexController {

    @Autowired
    UserService userService;

    @Autowired
    ProjectService projectService;

    @Autowired
    UserProjectService userProjectService;

    /**
     * 去往首页
     * @author Crescent_P
     * @date 2021-06-09 08:31:34
     * @return
     **/
    @GetMapping(value={"/","/index"})
    public String indexController(HttpSession session,Model model){
        if(session.getAttribute("userSession") == null) session.setAttribute("userSession",null);
        List<Project> projects = projectService.getAllProjects();
        model.addAttribute("projects",projects);
        return "index";
    }

    /**
     * 去到注册页面
     * @author Crescent_P
     * @date 2021-06-09 08:31:49
     * @return
     **/
    @GetMapping("/register.html")
    public String register(){
        return "register";
    }

    /**
     * 进行注册
     * @author Crescent_P
     * @date 2021-06-09 08:32:09
     * @param user
     * @param model
     * @return
     **/
    @PostMapping("/register")
    public String register(User user, Model model){
        String userName = user.getUsername();
        if(userService.getUserByName(userName)) {
            model.addAttribute("msg","用户名已被注册");
            return "register";
        }else{
            userService.saveUser(user);
//            System.out.println(user);
            return "redirect:/index";
        }
    }

    /**
     * 前往登录页面
     * @author Crescent_P
     * @date 2021-06-09 08:39:21
     * @return
     **/
    @GetMapping("/login.html")
    public String login(){
        // 前往登录页面
        return "login";
    }

    /**
     * 登录
     * @author Crescent_P
     * @date 2021-06-09 09:19:18
     * @param user
     * @param model
     * @param session
     * @return
     **/
    @RequestMapping("/login")
    public String login(User user, Model model, HttpSession session){
        if(userService.getUserByNameAndPassword(user)){
            // 登录成功
            User userByNameUser = userService.getUserByNameUser(user.getUsername());
            session.setAttribute("userSession",userByNameUser);
            System.out.println("debug==>" + userByNameUser);

            return "redirect:/index";
        }else{
            model.addAttribute("msg","用户名或密码错误");
            return "login";
        }
    }

    /**
     * 注销
     * @author Crescent_P
     * @date 2021-06-09 09:19:57
     * @return
     **/
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        // 注销
        session.removeAttribute("userSession");
        return "redirect:/index";
    }

    /**
     * 去往table页面
     * @author Crescent_P
     * @date 2021-06-10 08:09:46
     * @return
     **/
    @RequestMapping("/table")
    public String goTable(HttpSession session,Model model){
        Object userSession = session.getAttribute("userSession");
        if(userSession == null) {
            model.addAttribute("msg","请先登录");
            return "login";
        }
        return "table";
    }

    /**
     * 保存表
     * @author Crescent_P
     * @date 2021-06-10 08:10:02
     * @param project
     * @return
     **/
    @PostMapping("/saveTable")
    public String saveProject(Project project,HttpSession session){
        // 存表
         projectService.saveProject(project);
//        System.out.println(project);
        // 找表id
         Integer projectId = projectService.getProject(project).getId();
//        System.out.println("debug==>" + projectId);
        // 找用户
         User user = (User) session.getAttribute("userSession");
//        System.out.println("debug==>" + user);
         Integer userId = userService.getUserByNameUser(user.getUsername()).getId();
        // 存中间表
         userProjectService.saveUserProjectMapper(new UserProject(userId,projectId));
        return "redirect:/index";
    }

    /**
     * 去往我的
     * @author Crescent_P
     * @date 2021-06-10 08:10:21
     * @param session
     * @param model
     * @return
     **/
    @RequestMapping("/My")
    public String MyIndex(HttpSession session,Model model){
        User userSession = (User) session.getAttribute("userSession");
        if(userSession == null) {
            model.addAttribute("msg","请先登录");
            return "redirect:/login";
        }
        Integer userId = userSession.getId();
        List<Project> projects = projectService.getMyProjects(userId);
        model.addAttribute("MyProjects", projects);

        List<Project> AllProjects = projectService.getMyAllProject(userId);
        for (Project allProject : AllProjects) {
            System.out.println(allProject);
        }
        model.addAttribute("AllProjects", AllProjects);

        return "myIndex";
    }

    /**
     * 去往确认删除页面
     * @author Crescent_P
     * @date 2021-06-10 14:47:59
     * @return
     **/
    @RequestMapping("/check/{id}")
    public String checkOut(Model model,@PathVariable Integer id){
        System.out.println(id);
        Project project = projectService.getProjectById(id);
        model.addAttribute("project",project);
        return "check-out";
    }

    /**
     * 删除Project
     * @author Crescent_P
     * @date 2021-06-10 15:33:33
     * @return
     **/
    @RequestMapping("/deleteProject")
    public String deleteProject(@RequestParam String check,@RequestParam Integer id){
//        System.out.println("Debug==>" + id);
        if(check.equals("1")) projectService.deleteProject(id);

        return "redirect:/My";
    }

    /**
     * 去更新项目页面
     * @author Crescent_P
     * @date 2021-06-10 15:49:10
     * @return
     **/
    @RequestMapping("/updateProject/{id}")
    public String updateProject(Model model,@PathVariable Integer id){
        Project project = projectService.getProjectById(id);
        model.addAttribute("project",project);
        return "updateTable";
    }

    /**
     * 更新项目
     * @author Crescent_P
     * @date 2021-06-10 16:05:45
     * @return
     **/
    @RequestMapping("/updateTable")
    public String updateProject(Project project){
        System.out.println(project);
        projectService.updateProject(project);
        return "redirect:/My";
    }


    /**
     * 进入项目详情页
     * @author Crescent_P
     * @date 2021-06-10 19:09:20
     * @param id
     * @param model
     * @return
     **/
    @RequestMapping("/detail/{id}")
    public String detail(@PathVariable Integer id,Model model){
        System.out.println(id);
        Project project = projectService.getProjectById(id);
        Integer userId = project.getPrincipalId();
        User user = userService.getUserById(userId);
        model.addAttribute("user",user);
        model.addAttribute("project", project);
        return "detail";
    }

    /**
     * 进入确认加入页面
     * @author Crescent_P
     * @date 2021-06-10 19:09:33
     * @return
     **/
    @RequestMapping("/checkJoin/{id}")
    public String checkJoin(@PathVariable Integer id,Model model,HttpSession session){
        if(session.getAttribute("userSession") == null){
            model.addAttribute("msg","请先登录");
            return "login";
        }
        model.addAttribute("id", id);
        System.out.println("Debug==> checkJoin" + id);
        return "checkJoin";
    }

    /**
     * 加入项目
     * @author Crescent_P
     * @date 2021-06-10 19:28:48
     * @param check
     * @param projectId
     * @param session
     * @return
     **/
    @RequestMapping("/JoinProject")
    public String JoinProject(@RequestParam String check,@RequestParam Integer projectId,HttpSession session){
        if("1".equals(check)){
            UserProject userProject = new UserProject();
            User user = (User) session.getAttribute("userSession");
            Integer userId = userService.getUserByNameUser(user.getUsername()).getId();
            userProject.setUserId(userId);
            userProject.setProjectId(projectId);
            System.out.println(userProject);
            userProjectService.saveUserProjectMapper(userProject);
        }
        return "redirect:/index";
    }

    /**
     * 去往确认退出页面
     * @author Crescent_P
     * @date 2021-06-10 14:47:59
     * @return
     **/
    @RequestMapping("/checkLogout/{id}")
    public String checklogOut(Model model,@PathVariable Integer id){

         Project project = projectService.getProjectById(id);
         model.addAttribute("project",project);
         return "checklogout";
    }

    /**
     * 删除UserProject
     * @author Crescent_P
     * @date 2021-06-10 15:33:33
     * @return
     **/
    @RequestMapping("/LogoutProject")
    public String deleteUserProject(@RequestParam String check,@RequestParam Integer id,HttpSession session){
//        System.out.println("Debug==>" + id);
        User user = (User) session.getAttribute("userSession");
//        System.out.println(user);
        Integer userId = user.getId();
        if(check.equals("1")) userProjectService.deleteUserProject(userId,id);

        return "redirect:/My";
    }



    @RequestMapping("/searchProject")
    public String searchProject(Model model,String name){
        List<Project> projects = projectService.getProjectsByName(name);

        model.addAttribute("projects",projects);
        return "index";
    }

    /**
     * 项目负责人进入项目详情页
     * @author Crescent_P
     * @date 2021-06-13 13:51:08
     * @param id
     * @param model
     * @return
     **/
    @RequestMapping("/detailProject/{id}")
    public String detailProject(@PathVariable Integer id,Model model){
        System.out.println(id);
        Project project = projectService.getProjectById(id);
        List<Integer> userIds = userProjectService.getUserIds(id);
        List<User> users = new ArrayList<User>();
        for (Integer userId : userIds) {
            User user = userService.getUserById(userId);
            if(user != null) users.add(user);
        }
        Integer userId = project.getPrincipalId();
        User user = userService.getUserById(userId);
        for (User user1 : users) {
            System.out.println(user1);
        }
        model.addAttribute("user",user);
        model.addAttribute("project", project);
        model.addAttribute("users", users);
        return "detailProject";
    }
}
