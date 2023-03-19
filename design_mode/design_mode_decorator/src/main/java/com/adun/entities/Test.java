package com.adun.entities;

/**
 * @author ADun
 * @date 2022/12/28 22:35
 */
public class Test {

    public static void main(String[] args) {
        //起初 走路
        Person person = new SimplePerson();
        //装饰一下 配个单车
        BikeDecorator bikeDecorator = new BikeDecorator(person);
        //再装饰一下 配个汽车
        CarDecorator carDecorator = new CarDecorator(bikeDecorator);
        carDecorator.run();

    }
}
