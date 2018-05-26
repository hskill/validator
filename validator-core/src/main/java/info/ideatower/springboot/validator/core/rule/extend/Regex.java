package info.ideatower.springboot.validator.core.rule.extend;

import info.ideatower.springboot.validator.core.ValidatorContext;
import info.ideatower.springboot.validator.core.result.ValidatorResult;
import info.ideatower.springboot.validator.core.rule.AbstractRule;
import lombok.Getter;

import java.util.regex.Pattern;

/**
 * 按正则表达式验证是否符合
 * <p>
 * <code>
 *   regex: ^123$
 *
 *   regex:
 *     value: ^123$
 *     message: 不是正确的数据格式
 * </code>
 */
public class Regex extends AbstractRule {

    private Pattern regex;

    @Getter
    private String value;

    public void setValue(String value) {
        this.regex = Pattern.compile(value);
        this.value = value;
    }

    @Override
    public boolean isValid(ValidatorContext context, Object target, ValidatorResult errors) {

        if (target == null) {
            return true;
        }

        if (!regex.matcher(String.valueOf(target)).matches()) {
            reject(errors, context.getCurrentField().getName(), "{0} 不是正确的数据格式", target);
        }

        return false;
    }
}
