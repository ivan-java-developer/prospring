package com.prospring.ch10.services;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = CountrySingerValidator.class)
@Documented
public @interface CheckCountrySinger {
    String message() default "Country singer should have last name defined";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
