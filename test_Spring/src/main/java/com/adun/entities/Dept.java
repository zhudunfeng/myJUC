package com.adun.entities;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author Zhu Dunfeng
 * @date 2022/3/11 18:10
 */
@Data
@ToString
public class Dept<T> {
    private String id;

    private List<T> resultList;

}
