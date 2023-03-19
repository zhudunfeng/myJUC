package com.adun.design_mode_builder.entities;

/**
 * GPS导航器
 * @author ADun
 * @date 2023/1/1 23:08
 */
public class GPSNavigator {
    private String route;

    public GPSNavigator() {
        this.route = "221b, Baker Street, London  to Scotland Yard, 8-10 Broadway, London";
    }

    public GPSNavigator(String manualRoute){
        this.route = manualRoute;
    }

    /**
     * @return 路线
     */
    public String getRoute() {
        return route;
    }
}
