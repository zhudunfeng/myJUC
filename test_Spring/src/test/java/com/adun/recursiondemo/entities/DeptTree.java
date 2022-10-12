package com.adun.recursiondemo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ADun
 * @date 2022/10/6 15:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeptTree {
    private Dept root;
}
