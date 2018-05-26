package info.ideatower.springboot.validator.core.rule.extend;

import info.ideatower.springboot.validator.core.ValidatorContext;
import info.ideatower.springboot.validator.core.result.ValidatorResult;
import info.ideatower.springboot.validator.core.rule.AbstractRule;
import lombok.Data;

import java.util.regex.Pattern;

/**
 * 检测是否是ipv4地址
 */
@Data
public class Ip extends AbstractRule {

    /**
     * ipv4地址的正则表达式
     */
    protected static final Pattern IP_REGEX = Pattern.compile(
            "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)");

    private String regex = IP_REGEX.toString();

    @Override
    public boolean isValid(ValidatorContext context, Object target, ValidatorResult errors) {
        if (target == null) {
            return true;
        }

        if (IP_REGEX.matcher(String.valueOf(target)).matches()) {
            String objectName = context.getCurrentField().getName();
            reject(errors, objectName, "{0}[{1}]不是合法的ip地址值", objectName, target);
            return false;
        }

        return false;
    }
}
