package com.example.lab_02.model;

import java.util.List;

public class Validator {
    public boolean isNumeric(List<String> list) {
        String regex = "-?\\d+(\\.\\d+)?";
        return list.stream().allMatch(x -> x.matches(regex));
    }
}
