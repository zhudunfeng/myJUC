package com.adun.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Zhu Dunfeng
 * @date 2022/3/11 18:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("users")
public class User {

    public User(String userName,Integer age){
        super();
        this.userName=userName;
        this.age=age;
    }

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String userName;

    private Integer age;
}
