package com.adun.design_mode_builder.entities;

/**
 * 行车电脑
 * @author ADun
 * @date 2023/1/1 23:07
 */
public class TripComputer {
    private Car car;

    public void setCar(Car car) {
        this.car = car;
    }

    /**
     * 显示当前车辆燃料状态
     */
    public void showFuelLevel(){
        System.out.println("Fuel level: " + car.getFuel());
    }

    /**
     * 显示当前车辆引擎状态
     */
    public void showStatus(){
        if (this.car.getEngine().isStarted()) {
            System.out.println("Car is started");
        }else{
            System.out.println("Car isn't started");
        }
    }
}
