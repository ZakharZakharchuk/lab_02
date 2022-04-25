package com.example.lab_02.model;

public class Calculator {
    public double calculateTask(double a, double b, double c, double d){
        return Math.pow(Math.tan(a), 1 / c) /
                (1 + (Math.exp(b) - Math.exp(-b)) / (2 * Math.log(Math.abs(c + d))));
    }
}
