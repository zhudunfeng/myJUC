package com.adun.test_fastjson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author ADun
 * @date 2023/6/5 9:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class Student {

    private String userName;

    private String phoneNumber;

    private List<String> address;
}
