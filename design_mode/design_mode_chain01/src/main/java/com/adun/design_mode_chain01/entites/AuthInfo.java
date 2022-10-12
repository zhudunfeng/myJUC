package com.adun.design_mode_chain01.entites;

import lombok.Data;

/**
 * @author ADun
 * @date 2022/8/7 12:10
 * 返回结果的类
 */
@Data
public class AuthInfo {
    private String code;
    private String info = "";

    public AuthInfo(String code, String... infos) {
        this.code = code;
        for (String str : infos) {
            this.info = this.info.concat(str);
        }
    }
}
