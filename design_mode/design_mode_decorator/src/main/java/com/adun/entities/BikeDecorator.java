package com.adun.entities;

/**
 * @author ADun
 * @date 2022/12/28 22:33
 */
public class BikeDecorator extends AbstractPersonDecorator {
    public BikeDecorator(Person person) {
        super(person);
    }

    @Override
    public void run() {
        super.person.run();
        System.out.println("骑单车");
    }
}
