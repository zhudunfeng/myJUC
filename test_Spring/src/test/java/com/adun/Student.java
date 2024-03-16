package com.adun;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author zhudunfeng
 * @date 2021/6/16 9:53
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student implements Cloneable {
    private String id;
    private String name;
    private Integer age;
    private Address address;
//    private Address[] addresses;

    public Student(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    protected Student clone() throws CloneNotSupportedException {
        Student student = (Student) super.clone();
        student.address = (Address)address.clone();
        return student;
    }
}