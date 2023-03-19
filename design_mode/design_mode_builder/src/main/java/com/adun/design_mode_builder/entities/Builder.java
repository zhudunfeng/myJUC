package com.adun.design_mode_builder.entities;

/**
 *  通用生成器接口
 * @author ADun
 * @date 2023/1/1 22:58
 */
public interface Builder {

    /**
     * 设置汽车类型
     * @param  carType 汽车类型
     */
    void setCarType(CarType carType);

    /**
     * 设置座椅数目
     * @param seats 座椅数
     */
    void setSeats(int seats);

    /**
     * 设置引擎
     * @param engine 引擎信息
     */
    void setEngine(Engine engine);

    /**
     * 设置播放器
     * @param transmission 播放器信息
     */
    void setTransmission(Transmission transmission);

    /**
     * 设置行车电脑
     * @param tripComputer 行车电脑信息
     */
    void setTripComputer(TripComputer tripComputer);

    /**
     * 设置GPS导航器
     * @param gpsNavigator GPS导航器信息
     */
    void setGPSNavigator(GPSNavigator gpsNavigator);
}
