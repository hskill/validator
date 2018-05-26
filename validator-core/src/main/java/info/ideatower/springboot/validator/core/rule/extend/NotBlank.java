package info.ideatower.springboot.validator.core.rule.extend;

import info.ideatower.springboot.validator.core.ValidatorContext;
import info.ideatower.springboot.validator.core.result.ValidatorResult;
import info.ideatower.springboot.validator.core.rule.AbstractRule;
import lombok.Data;
import lombok.val;

/**
 * 不能为空字符串与空白字符串
 * <code>
 * notBlank:
 *   message:    # 指定的错误消息
 * </code>
 */
@Data
public class NotBlank extends AbstractRule {

    @Override
    public boolean isValid(ValidatorContext context, Object target, ValidatorResult errors) {

        if (target == null) {
            return true;
        }

        if (((String) target).trim().equals("")) {
            String objectName = context.getCurrentField().getName();
            reject(errors, objectName, "{0} 值为空字符串", objectName);
            return false;
        }
        return true;
    }
}
