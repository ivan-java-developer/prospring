package com.prospring.ch4.profile.kindergarten;

import com.prospring.ch4.profile.Food;
import com.prospring.ch4.profile.FoodProviderService;

import java.util.ArrayList;
import java.util.List;

public class FoodProviderServiceImpl implements FoodProviderService {
    @Override
    public List<Food> provideLunchSet() {
        ArrayList<Food> lunchSet = new ArrayList<>();
        lunchSet.add(new Food("milk"));
        lunchSet.add(new Food("biscuit"));
        return lunchSet;
    }
}
