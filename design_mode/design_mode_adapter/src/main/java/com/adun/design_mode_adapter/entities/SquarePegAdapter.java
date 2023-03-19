package com.adun.design_mode_adapter.entities;

/**
 * 方钉到圆孔的适配器
 *
 * @author ADun
 * @date 2022/12/30 0:37
 */
public class SquarePegAdapter extends RoundPeg {

    // 在实际情况中，适配器中会包含一个 SquarePeg 类的实例。
    private SquarePeg peg;

    public SquarePegAdapter(SquarePeg peg) {
        this.peg = peg;
    }

    @Override
    public double getRadius() {
        double result;
        result = (Math.sqrt(Math.pow((peg.getWidth() / 2), 2) * 2));
        return result;
    }
}

