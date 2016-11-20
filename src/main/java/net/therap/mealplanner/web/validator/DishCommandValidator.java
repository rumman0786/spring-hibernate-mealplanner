package net.therap.mealplanner.web.validator;

import net.therap.mealplanner.web.command.DishCommand;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author rumman
 * @since 11/16/16
 */
public class DishCommandValidator implements Validator {

    @Override
    public boolean supports(Class clazz) {
        return DishCommand.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        DishCommand command = (DishCommand) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "dishname.required");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "calories", "dishcalories.required");

    }
}
