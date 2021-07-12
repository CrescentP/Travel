package com.caopeng.travel.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Crescent_P
 * @date 2021-06-09 19:37
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProject {

    private Integer id;
    private Integer userId;
    private Integer projectId;

    public UserProject(Integer userId, Integer projectId) {
        this.userId = userId;
        this.projectId = projectId;
    }
}
