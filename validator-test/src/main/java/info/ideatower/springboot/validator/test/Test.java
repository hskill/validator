package info.ideatower.springboot.validator.test;

import info.ideatower.springboot.validator.core.ValidatorContext;
import info.ideatower.springboot.validator.core.result.ObjectError;
import info.ideatower.springboot.validator.core.result.ValidatorResult;
import info.ideatower.springboot.validator.core.rule.AbstractRule;

public class Test extends AbstractRule {

    @Override
    public boolean isValid(ValidatorContext context, Object target, ValidatorResult errors) {
        if (target == null) {
            return true;
        }

        if (target.equals("xxx")) {
            return true;
        }

        this.reject(errors, context.getCurrentField().getName(), "name 必须等于 xxx");
        return false;
    }
}
