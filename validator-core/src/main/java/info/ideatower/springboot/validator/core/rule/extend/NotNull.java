package info.ideatower.springboot.validator.core.rule.extend;

import info.ideatower.springboot.validator.core.ValidatorContext;
import info.ideatower.springboot.validator.core.result.ValidatorResult;
import info.ideatower.springboot.validator.core.rule.AbstractRule;
import lombok.Data;


/**
 * 是否必须
 */
@Data
public class NotNull extends AbstractRule {

    @Override
    public boolean isValid(ValidatorContext context, Object target, ValidatorResult errors) {

        if (target == null) {
            String objectName = context.getCurrentField().getName();
            reject(errors, objectName, "{0} 不存在", objectName);
            return false;
        }
        return true;
    }
}
