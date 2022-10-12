package com.adun;

/**
 * @author ADun
 * @date 2022/8/1 15:16
 * <p>
 * 面试题：equals与==的区别
 * <p>
 * ==是比较的栈中变量的值
 * equals不重写与==一样，重写后按重写规则进行比较
 */
public class EqualsDemo {

    public static void main(String[] args) {
        int a = 10;
        int b = 10;
        System.out.println(a == b);

        Car car1 = new Car("奔驰A300L");
        Car car2 = new Car("奔驰A300L");
        System.out.println(car1 == car2);
        System.out.println(car1.equals(car2));
    }

}

class Car {
    private String name;

    public Car() {
        super();
    }

    public Car(String name) {
        super();
        this.name = name;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Car) {
            Car car = (Car) obj;
            return this.name.equals(car.name);
        }

        return false;
    }
}
