package com.adun;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author Zhu Dunfeng
 * @date 2021/11/22 17:31
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public final class Address {
    private String address;

    private int num;

    private List standbyList;
}
