package com.caopeng.travel.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Crescent_P
 * @date 2021-06-07 20:05
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;
    private String username;
    private String password;
    private String realname;
    private String telephone;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String realname, String telephone) {
        this.username = username;
        this.password = password;
        this.realname = realname;
        this.telephone = telephone;
    }
}
