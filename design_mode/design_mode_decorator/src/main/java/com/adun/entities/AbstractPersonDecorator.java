package com.adun.entities;

/**
 * @author ADun
 * @date 2022/12/28 22:31
 */
public abstract class AbstractPersonDecorator implements Person{

    protected final Person person;

    public AbstractPersonDecorator(Person person) {
        this.person = person;
    }
}
