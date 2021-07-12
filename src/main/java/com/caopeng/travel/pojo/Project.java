package com.caopeng.travel.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Crescent_P
 * @date 2021-06-09 10:11
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    // id
    private Integer id;
    // 活动名称
    private String name;
    // 目的地
    private String destination;
    // 详情
    private String details;
    // 预算
    private double money;

    // 开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date begin;
    // 结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end;

    // 负责人id
    private Integer principalId;

    public Project(String name, String destination, String details, double money, Date begin, Date end, Integer principalId) {
        this.name = name;
        this.destination = destination;
        this.details = details;
        this.money = money;
        this.begin = begin;
        this.end = end;
        this.principalId = principalId;
    }
}
