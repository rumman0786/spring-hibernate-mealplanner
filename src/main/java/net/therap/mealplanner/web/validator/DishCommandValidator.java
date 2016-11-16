package net.therap.mealplanner.web.validator;

import net.therap.mealplanner.web.command.DishCommand;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author rumman
 * @since 11/16/16
 */
public class DishCommandValidator implements Validator {

    public boolean supports(Class clazz) {
        return DishCommand.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {

        DishCommand command = (DishCommand) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "dishname.required");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "calories", "dishcalories.required");

//
//        if (command.getName() == MedAdministrationRecordType.UNDEFINED) {
//            errors.reject("mar.error.record.type.required", "Record type is required.");
//        }
//
//        String administerTimeStr = command.getAdministerTime();
//        if (command.getMedType() == MedicationTypeConstants.PRN_MEDICATION || command.getMedType() == MedicationTypeConstants.PRN_TREATMENT) {
//            if (StringUtils.isEmpty(administerTimeStr)) {
//                errors.reject("mar.error.administer.time.required", "Administer time is required.");
//            }
//        }
//
//        if ((command.getRecordType() == MedAdministrationRecordType.DELETED) && (command.getId() == 0)) {
//            errors.reject("mar.error.delete.new.data", "Slot cannot be Deleted before the form is saved.");
//        }
    }
}
