package info.ideatower.springboot.validator.core.rule.extend;

import info.ideatower.springboot.validator.core.ValidatorContext;
import info.ideatower.springboot.validator.core.result.ValidatorResult;
import info.ideatower.springboot.validator.core.rule.AbstractRule;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.text.MessageFormat;

/**
 * 不能小于最小值
 * <p>
 * <code>
 * # example 1
 * min: 2           # 指定的值
 * <p>
 * # example 2
 * min:
 *   value: 16    # 指定的值
 *   message:     # 指定的消息
 * </code>
 */
@Data
public class Min extends AbstractRule {

    private Double value = Double.MIN_VALUE;

    @Override
    public boolean isValid(ValidatorContext context, Object target, ValidatorResult errors) {

        if (target == null) {
            return true;
        }

        Double t = NumberUtils.createDouble(String.valueOf(target));
        if (t == null) {
            String objectName = context.getCurrentField().getName();
            reject(errors, objectName, INVALID_NUMBER_MESSAGE_FORMAT, objectName);
            return false;
        }

        if (t <= this.value) {
            String objectName = context.getCurrentField().getName();
            reject(errors, objectName, "{0} 的数字值 {1} 小于 {2}", objectName, target, this.value);
            return false;
        }

        return true;
    }
}
