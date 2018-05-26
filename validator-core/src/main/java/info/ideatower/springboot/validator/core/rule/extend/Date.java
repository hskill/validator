package info.ideatower.springboot.validator.core.rule.extend;

import info.ideatower.springboot.validator.core.ValidatorContext;
import info.ideatower.springboot.validator.core.result.ValidatorResult;
import info.ideatower.springboot.validator.core.rule.AbstractRule;
import lombok.ToString;

/**
 * TODO
 */
@ToString
public class Date extends AbstractRule {

    @Override
    public boolean isValid(ValidatorContext context, Object target, ValidatorResult errors) {
        if (target == null) {
            return true;
        }

        if (target instanceof java.util.Date) {
            return true;
        }

        // TODO

        return false;
    }
}
