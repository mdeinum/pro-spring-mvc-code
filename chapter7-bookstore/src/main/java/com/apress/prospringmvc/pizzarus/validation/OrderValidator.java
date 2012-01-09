package com.apress.prospringmvc.pizzarus.validation;

import org.h2.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.apress.prospringmvc.pizzarus.domain.Order;

public class OrderValidator implements Validator {

    @Override
    public boolean supports(final Class<?> clazz) {
        return (Order.class).isAssignableFrom(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required", new Object[] { "Name" });
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "required", new Object[] { "Address" });
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "required", new Object[] { "City" });
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "required", new Object[] { "Country" });
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "creditcard", "required", new Object[] { "Creditcard" });

        Order order = (Order) target;
        if (!errors.hasFieldErrors("creditcard")) {
            if (order.getCreditcard().length() != 16 || !StringUtils.isNumber(order.getCreditcard())) {
                errors.rejectValue("creditcard", "invalid");
            }
        }

        if (order.getLines().isEmpty()) {
            errors.rejectValue("lines", "required", new Object[] { "lines" }, null);
        }

    }

}
