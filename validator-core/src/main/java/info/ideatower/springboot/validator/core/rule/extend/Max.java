package info.ideatower.springboot.validator.core.rule.extend;

import info.ideatower.springboot.validator.core.ValidatorContext;
import info.ideatower.springboot.validator.core.result.ValidatorResult;
import info.ideatower.springboot.validator.core.rule.AbstractRule;
import lombok.Data;
import org.apache.commons.lang3.math.NumberUtils;

import java.text.MessageFormat;

/**
 * 不能超过最大值
 * <p>
 * 类似的还有
 *
 * @see Between,
 * @see Min
 * <p>
 * <code>
 * max:
 *   value: 16    # 指定的值
 *   message:     # 指定的消息
 * </code>
 */
@Data
public class Max extends AbstractRule {

    private Double value = Double.MAX_VALUE;

    public void setValue(String value) {
        if (!NumberUtils.isNumber(value)) {
            throw new IllegalArgumentException(MessageFormat.format("Max的参数值[{0}]不是数字", value));
        }
    }

    @Override
    public boolean isValid(ValidatorContext context, Object target, ValidatorResult errors) {
        // 对于无法判断的值，直接跳过
        if (target == null) {
            return true;
        }

        Double t = NumberUtils.createDouble(String.valueOf(target));
        if (t == null) {
            String objectName = context.getCurrentField().getName();
            reject(errors, objectName, INVALID_NUMBER_MESSAGE_FORMAT, objectName);
            return false;
        }

        if (t >= this.value) {
            String objectName = context.getCurrentField().getName();
            reject(errors, objectName, "{0} 的数字值 {1} 超过 {2}", objectName, target, this.value);
            return false;
        }
        return true;
    }
}
