package com.example.rmc.customAdapters;

public class FoodTestModel {

    String testName;
    int id;

    public FoodTestModel(String testName, int id) {
        this.testName = testName;
        this.id = id;
    }

    public String getTestName() {
        return testName;
    }

    public int getId() {
        return id;
    }
}
