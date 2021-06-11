package com.adun.test_variable;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author zhudunfeng
 * @date 2021/6/10 13:34
 */
public class VarParamTest {
    @Test
    public void varParam(){
        List<String> list1 = new ArrayList<>();
        list1.add("aa");
        list1.add("bb");
        List<String> list2 = new ArrayList<>();
        list2.add("aa");
        list2.add("cc");
        List<String> list3 = new ArrayList<>();
        list3.add("cc");
        list3.add("ff");
        List<String> deDupList = getDeDupList(list1, list2, list3);
        System.out.println(deDupList.toString());
    }

    private List<String> getDeDupList(List<String>... lists) {
        HashSet<String> deDupSet = new HashSet<>();
        for (List<String> list : lists) {
            deDupSet.addAll(list);
        }
        List<String> deDuplist = new ArrayList<>(deDupSet);
        return deDuplist;
    }


}
