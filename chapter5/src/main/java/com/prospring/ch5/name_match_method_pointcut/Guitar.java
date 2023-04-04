package com.prospring.ch5.name_match_method_pointcut;

public class Guitar {
    private String brand = "Simple brand";

    public String play() {
        return "брынь брынь брынь";
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
