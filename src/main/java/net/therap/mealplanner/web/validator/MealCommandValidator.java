package net.therap.mealplanner.web.validator;

import net.therap.mealplanner.web.command.DishCommand;
import net.therap.mealplanner.web.command.MealCommand;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author rumman
 * @since 11/17/16
 */
public class MealCommandValidator implements Validator {

    public boolean supports(Class clazz) {
        return MealCommand.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        System.out.println("some thing");
        MealCommand command = (MealCommand) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "mealName.required");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "day", "day.required");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "menuType", "menuType.required");

    }
}
