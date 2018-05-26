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
public class Email extends AbstractRule {

    protected static final String DEFAULT_MESSAGE_FORMAT = "{0} 不是正确的电子邮件格式";

    /**
     * 验证固定电话格式的正则表达式
     */
    protected static final Pattern EMAIL_REGEX = Pattern.compile(
            "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");

    private String regex = EMAIL_REGEX.toString();

    @Override
    public boolean isValid(ValidatorContext context, Object target, ValidatorResult errors) {
        if (target == null) {
            return true;
        }

        if (!EMAIL_REGEX.matcher(String.valueOf(target)).matches()) {
            String objectName = context.getCurrentField().getName();
            reject(errors, objectName, DEFAULT_MESSAGE_FORMAT, objectName);
            return false;
        }

        return true;
    }
}
