package com.adun.test_enum;

/**
 * @author ADun
 * @date 2022/8/26 16:40
 */
public enum Season {

    //春天
    SPRING("ADUN"),
    //夏天
    SUMMER,
    //秋天
    AUTUMN,
    //冬天
    WINTER;

    private String value;

    private Season(){}

    private Season(String value){
        this.value=value;
    }

    @Override
    public String toString() {
        String vStr= this.value==null? "":this.value;
        return super.toString()+"("+vStr+")";
    }
}
