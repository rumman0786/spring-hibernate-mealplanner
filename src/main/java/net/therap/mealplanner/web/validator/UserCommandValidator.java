package net.therap.mealplanner.web.validator;

import net.therap.mealplanner.web.command.UserCommand;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author rumman
 * @since 11/16/16
 */
public class UserCommandValidator implements Validator {

    @Override
    public boolean supports(Class clazz) {
        return UserCommand.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        UserCommand command = (UserCommand) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.required");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "firstName.required");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "lastName.required");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.required");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "confirmPassword.required");


        if (!command.getPassword().equals(command.getConfirmPassword())) {
            errors.rejectValue("password", "bothPassword", new Object[]{"'Both Passwords'"}, "");
        }
    }
}
