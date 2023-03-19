package com.adun.design_mode_adapter.entities;

/**
 * 圆孔
 *
 * @author ADun
 * @date 2022/12/30 0:22
 */
public class RoundHole {

    /**
     * 半径
     */
    private double radius;

    public RoundHole(double radius) {
        this.radius = radius;
    }

    public double getRadius(){
        return this.radius;
    }


    /**
     * 圆孔是否能通过圆钉
     * @param peg
     * @return
     */
    public boolean fits(RoundPeg peg){
        boolean result;
        result = (this.radius>=peg.getRadius());
        return result;
    }

}
