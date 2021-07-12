package com.caopeng.travel.Service;

import com.caopeng.travel.mapper.UserMapper;
import com.caopeng.travel.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Crescent_P
 * @date 2021-06-07 20:07
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 存储用户
     * @author Crescent_P
     * @date 2021-06-09 08:44:51
     * @param user
     * @return
     **/
    public int saveUser(User user){
        return userMapper.saveUser(user);
    }

    /**
     * 根据用户名能否找到用户
     * @author Crescent_P
     * @date 2021-06-09 08:45:01
     * @param username
     * @return
     **/
    public boolean getUserByName(String username){
        // 存在用户名了
        if(userMapper.getUserByName(username) != null) return true;
        return false;
    }

    /**
     * 根据用户名找到用户
     * @author Crescent_P
     * @date 2021-06-10 09:07:44
     * @param username
     * @return
     **/
    public User getUserByNameUser(String username){
        return userMapper.getUserByName(username);
    }

    /**
     * 能否登录成功
     * @author Crescent_P
     * @date 2021-06-09 08:45:23
     * @param user
     * @return
     **/
    public boolean getUserByNameAndPassword(User user){
        String userName = user.getUsername();
        String password = user.getPassword();
        if(userMapper.getUserByNameAndPassword(userName,password) != null) return true;
        return false;
    }

    public User getUserById(Integer id) { return userMapper.getUserById(id);}


    public List<User> getAllUsers() { return userMapper.getAllUsers(); }
}
