package info.ideatower.springboot.validator.core.rule.extend;

import info.ideatower.springboot.validator.core.ValidatorContext;
import info.ideatower.springboot.validator.core.result.ValidatorResult;
import info.ideatower.springboot.validator.core.rule.AbstractRule;
import lombok.Data;
import lombok.ToString;

import java.util.regex.Pattern;

/**
 */
@Data
public class Url extends AbstractRule {

    protected static final String DEFAULT_MESSAGE_FORMAT = "{0} 不是正确的URL格式";


    protected static final Pattern URL_REGEX = Pattern.compile(
            "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");

    private String regex = URL_REGEX.toString();

    @Override
    public boolean isValid(ValidatorContext context, Object target, ValidatorResult errors) {

        if (target == null) {
            return true;
        }

        if (!URL_REGEX.matcher(String.valueOf(target)).matches()) {
            String objectName = context.getCurrentField().getName();
            reject(errors, objectName, "", objectName);
            return false;
        }

        return true;
    }
}
