package com.adun.bean;

import lombok.Data;

import java.util.Date;

@Data
public class Programmer {
    // 姓名
    private String name;
    // 项目
    private String project;
    // 模块
    private String module;
    // 进度
    private double schedule;
    // 计划完成时间
    private Date completePlanTime;
    // 详细信息
    private String info;
}
