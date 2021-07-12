package com.caopeng.travel.mapper;

import com.caopeng.travel.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Crescent_P
 * @date 2021-06-07 20:07
 */

@Mapper
@Repository
public interface UserMapper {
    public int saveUser(User user);

    public User getUserByName (String username);

    public User getUserByNameAndPassword(String username, String password);

    public User getUserById(Integer id);

    public List<User> getAllUsers();
}
