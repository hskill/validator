package info.ideatower.springboot.validator.core.rule.extend;

import info.ideatower.springboot.validator.core.ValidatorContext;
import info.ideatower.springboot.validator.core.result.ValidatorResult;
import info.ideatower.springboot.validator.core.rule.AbstractRule;
import lombok.Setter;
import lombok.ToString;
import lombok.val;

/**
 * 校验数据的长度必须等于整数值，或不能超过整数值范围
 * <code>
 * # example 1
 * size:
 *   value: 16   # 指定长度值
 *   message:    # 指定错误消息
 * # example 2
 * size:
 *   min: 12     # 指定长度最小值
 *   max: 16     # 指定长度最大值
 *   message:    # 指定错误消息
 * </code>
 */
@ToString
public class Size extends AbstractRule {

    protected static final String DEFAULT_SIZE_NOT_EQUALS_SOLID_VALUE_MESSAGE_FORMAT = "{0} 值长度不等于 {1}";

    protected static final String DEFAULT_SIZE_NOT_BEWTEEN_VALUES_MESSAGE_FORMAT = "{0} 值长度不在 {1} 与 {2} 之间";
    /**
     * 固定值
     */
    @Setter
    private int value = Integer.MIN_VALUE;

    /**
     * 最大值
     */
    @Setter
    private int min = Integer.MIN_VALUE;

    /**
     * 最小值
     */
    @Setter
    private int max = Integer.MAX_VALUE;

    @Override
    public boolean isValid(ValidatorContext context, Object target, ValidatorResult errors) {
        // 对于无法判断的值，直接跳过
        if (target == null) {
            return true;
        }

        // 如果有固定值，按固定值判断
        val length = String.valueOf(target).length();
        if (value > 0 && length != value) {
            String objectName = context.getCurrentField().getName();
            reject(errors, objectName, DEFAULT_SIZE_NOT_EQUALS_SOLID_VALUE_MESSAGE_FORMAT, objectName, value);
            return false;
        } else if (length >= min && length <= max) {
            String objectName = context.getCurrentField().getName();
            reject(errors, objectName, DEFAULT_SIZE_NOT_BEWTEEN_VALUES_MESSAGE_FORMAT, objectName, min, max);
            return false;
        }

        return true;
    }
}
