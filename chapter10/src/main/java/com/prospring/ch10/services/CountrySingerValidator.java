package com.prospring.ch10.services;

import com.prospring.ch10.objects.Genre;
import com.prospring.ch10.objects.Singer;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CountrySingerValidator implements ConstraintValidator<CheckCountrySinger, Singer> {

    @Override
    public boolean isValid(Singer singer, ConstraintValidatorContext context) {
        boolean result = true;
        if (singer.getGenre() != null
                && (Genre.COUNTRY == singer.getGenre() && singer.getLastName() == null)) {
            result = false;
        }
        return result;
    }
}
