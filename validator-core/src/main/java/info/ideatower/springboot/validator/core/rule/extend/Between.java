package info.ideatower.springboot.validator.core.rule.extend;

import info.ideatower.springboot.validator.core.ValidatorContext;
import info.ideatower.springboot.validator.core.result.ValidatorResult;
import info.ideatower.springboot.validator.core.rule.AbstractRule;
import info.ideatower.springboot.validator.core.rule.Converter;
import lombok.Data;

/**
 * 输入域的值在min与max范围内，只能用于数值
 * <code>
 * between:
 *   min: 12      # 指定最小值
 *   max: 22      # 指定最大值
 *   message:     # 指定错误消息
 * </code>
 */
@Data
public class Between extends AbstractRule {

    protected static final String DEFAULT_MESSAGE_FORMAT = "{0} 的数字值 {1} 超过 {2}";

    /** 最大值 */
    private Integer max = Integer.MAX_VALUE;

    /** 最小值 */
    private Integer min = Integer.MIN_VALUE;

    @Override
    public boolean isValid(ValidatorContext context, Object target, ValidatorResult errors) {
        if (target == null) {
            return true;
        }

        Number i = Converter.getNumber(target);
        if (i == null) {
            String objectName = context.getCurrentField().getName();
            reject(errors, objectName, INVALID_NUMBER_MESSAGE_FORMAT, objectName);
            return false;
        }

        if (i.intValue() > max || i.intValue() < min) {
            String objectName = context.getCurrentField().getName();
            reject(errors, objectName, DEFAULT_MESSAGE_FORMAT, objectName, target, i.intValue());
            return false;
        }

        return true;
    }
}
