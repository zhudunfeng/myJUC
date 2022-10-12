package com.adun.redis.entitys;

import lombok.Data;
import reactor.util.annotation.NonNull;

/**
 * @author zhudunfeng
 * @date 2021/6/3 9:48
 */
@Data
public class Student {

    private String id;

    private String name;

//    @NonNull()
    private String address;

}
