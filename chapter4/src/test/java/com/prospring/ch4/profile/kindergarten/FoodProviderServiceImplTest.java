package com.prospring.ch4.profile.kindergarten;

import com.prospring.ch4.config.HighSchoolProfileConfig;
import com.prospring.ch4.config.KindergartenProfileConfig;
import com.prospring.ch4.profile.FoodProviderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {KindergartenProfileConfig.class, HighSchoolProfileConfig.class})
@ActiveProfiles("kindergarten")
public class FoodProviderServiceImplTest {
    @Autowired
    FoodProviderService foodProviderService;

    @Test
    public void testProvider() {
        Assert.notNull(foodProviderService.provideLunchSet(), "The food list must not be null");
        Assert.notEmpty(foodProviderService.provideLunchSet(), "The food list must not be empty");
        Assert.state(foodProviderService.provideLunchSet().size() == 2, "Lunch set size doesn't equals 2");
        assertEquals(2, foodProviderService.provideLunchSet().size());

    }
}
