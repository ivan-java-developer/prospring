package com.prospring.ch4.config;

import com.prospring.ch4.profile.FoodProviderService;
import com.prospring.ch4.profile.kindergarten.FoodProviderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("kindergarten")
public class KindergartenProfileConfig {
    @Bean
    public FoodProviderService foodProvider() {
        return new FoodProviderServiceImpl();
    }
}
