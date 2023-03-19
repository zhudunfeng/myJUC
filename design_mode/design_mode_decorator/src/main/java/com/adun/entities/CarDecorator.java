package com.adun.entities;

/**
 * @author ADun
 * @date 2022/12/28 22:34
 */
public class CarDecorator extends AbstractPersonDecorator {
    public CarDecorator(Person person) {
        super(person);
    }

    @Override
    public void run() {
        super.person.run();
        System.out.println("开车");
    }
}
