package com.adun.design_mode_builder.entities;

/**
 * 引擎
 *
 * @author ADun
 * @date 2023/1/1 23:04
 */
public class Engine {

    private final double volume;
    private double mileage;
    private boolean started;

    /**
     * @param volume  体积
     * @param mileage 里程
     * @param started 发动的
     */
    public Engine(double volume, double mileage) {
        this.volume = volume;
        this.mileage = mileage;
    }

    public void on() {
        started = true;
    }

    public void off() {
        started = false;
    }

    public boolean isStarted(){
        return started;
    }

    public void go(double mileage){
        if (started) {
            this.mileage+=mileage;
        }else{
            System.out.println("Cannot go(), you must start engine first!");
        }
    }

    /**
     * @return 体积
     */
    public double getVolume() {
        return volume;
    }

    /**
     * @return 里程数
     */
    public double getMileage() {
        return mileage;
    }
}
