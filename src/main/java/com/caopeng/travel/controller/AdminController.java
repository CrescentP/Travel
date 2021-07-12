package com.caopeng.travel.controller;

import com.caopeng.travel.Service.ProjectService;
import com.caopeng.travel.Service.UserService;
import com.caopeng.travel.pojo.Project;
import com.caopeng.travel.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Crescent_P
 * @date 2021-06-08 11:53
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    ProjectService projectService;

    /**
     * 去往管理页面首页
     * @author Crescent_P
     * @date 2021-06-15 10:09:08
     * @return
     **/
    @GetMapping(value={"index","/",""})
    public String index(HttpSession session){
        User user = (User) session.getAttribute("userSession");

        if(user != null && user.getUsername().equals("Crescent_P")) return "admin/index";
        return "redirect:/index";
    }

    @RequestMapping("/user")
    public String getAllUser(Model model,HttpSession session){
        User user = (User) session.getAttribute("userSession");
        if(user == null || !user.getUsername().equals("Crescent_P")) return "redirect:/index";
        List<User> users = userService.getAllUsers();
        model.addAttribute("users",users);
        return "admin/user";
    }

    @RequestMapping("/project")
    public String getAllProject(Model model,HttpSession session){
        User user = (User) session.getAttribute("userSession");
        if(user == null || !user.getUsername().equals("Crescent_P")) return "redirect:/index";
        List<Project> projects = projectService.getAllProjects();
        model.addAttribute("projects",projects);
        return "admin/project";
    }
}
