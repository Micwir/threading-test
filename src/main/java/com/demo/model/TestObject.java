package com.demo.model;

import jakarta.enterprise.context.Dependent;

import java.util.UUID;

@Dependent
public class TestObject {

    private final UUID uuid;
    private int number1 = 0;
    private int number2 = 0;

    public TestObject() {
        uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    public int getNumber1() {
        return number1;
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public int getNumber2() {
        return number2;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }
}
