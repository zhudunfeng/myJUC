package com.adun.chain;


import com.adun.bean.Programmer;

public class Check {

    public boolean programmerCheck(Programmer programmer) {

        if (!"公众号".equals(programmer.getProject())) {
            return false;
        }

        if (!"哪吒编程".equals(programmer.getName())) {
            return false;
        }

        if (!programmer.getInfo().equals("公众号哪吒编程，定期分享Java干货，还有不定期的送书活动，包邮到你家，哈哈")) {
            return false;
        }

        return true;
    }
}
