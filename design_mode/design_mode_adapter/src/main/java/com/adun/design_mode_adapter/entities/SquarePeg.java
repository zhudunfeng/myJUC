package com.adun.design_mode_adapter.entities;

/**
 * 方钉
 * @author ADun
 * @date 2022/12/30 0:31
 */
public class SquarePeg {

    private double width;

    public SquarePeg(double width){
        this.width = width;
    }

    public double getWidth(){
        return this.width;
    }

    /**
     * 获取正方形的面积
     * @return
     */
    public double getSquare(){
        double result;
        result =  Math.pow(this.width, 2);
        return result;
    }

}

