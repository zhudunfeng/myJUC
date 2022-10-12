package com.adun.recursiondemo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ADun
 * @date 2022/10/6 15:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dept {

    private String id;
    private String pid;

    private String name;

    /**
     * 子部门
     */
    private List<Dept> children;


    public Dept(String id, String pid, String name) {
        this.id = id;
        this.pid = pid;
        this.name = name;
    }
}
