package com.adun.test_copyonwritearray;

import java.util.Arrays;

public class TestArrays {
    public static void main(String[] args) {
        int[] num=new int[3];
        //Arrays.fill(num,6);
        for (int i = 0; i <3 ; i++) {
            num[i]=3-i;
        }
        System.out.println(Arrays.toString(num));
        Arrays.parallelSort(num);
        System.out.println(Arrays.toString(num));
    }
}
