package info.ideatower.springboot.validator.core.rule.extend;

import info.ideatower.springboot.validator.core.ValidatorContext;
import info.ideatower.springboot.validator.core.result.ValidatorResult;
import info.ideatower.springboot.validator.core.rule.AbstractRule;
import lombok.Data;
import lombok.val;

/**
 * 确认输入域中两个值是否一致
 * <code>
 * confirm:
 *   field: password   # 指定其它域名称
 *   message:          # 指定错误输出消息
 * </code>
 */
@Data
public class EqualsTo extends AbstractRule {

    /**
     * 错误消息说明
     */
    protected static final String DEFAULT_MESSAGE_FORMAT = "{0} 值与 {1} 的值不相等";

    private String field;

    public boolean isValid(ValidatorContext context, Object target, ValidatorResult errors) {
        // 对于无法判断的值，直接跳过
        if (target == null) {
            return true;
        }


        val notBlank = new NotBlank();
        // relatedObject中包含了指定输入域，且其值不为空
        if (context.getRelated().contains(this.field)
                && notBlank.isValid(context, context.getRelated().getValue(this.field), errors)
                && !target.equals(context.getRelated().getValue(this.field))
                ) {

            // 如果与对应的filed值不相等，则校验不通过
            String objectName = context.getCurrentField().getName();
            reject(errors, objectName, DEFAULT_MESSAGE_FORMAT, this.field, objectName);
            return false;
        }
        return true;
    }
}
