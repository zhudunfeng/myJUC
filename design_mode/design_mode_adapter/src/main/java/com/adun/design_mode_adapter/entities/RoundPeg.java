package com.adun.design_mode_adapter.entities;

/**
 * 圆钉
 * @author ADun
 * @date 2022/12/30 0:26
 */
public class RoundPeg {
    /**
     * 半径
     */
    private double radius;

    public RoundPeg(){}

    public RoundPeg(double radius) {
        this.radius = radius;
    }

    public double getRadius(){
        return this.radius;
    }
}
