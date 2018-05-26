package info.ideatower.springboot.validator.core.rule.extend;

import info.ideatower.springboot.validator.core.ValidatorContext;
import info.ideatower.springboot.validator.core.result.ValidatorResult;
import info.ideatower.springboot.validator.core.rule.AbstractRule;
import lombok.Data;

import java.util.regex.Pattern;

/**
 * 中国固定电话号码
 * <code>
 * chineseTelPhone: ~
 * chineseTelPhone:
 *   message: 非正确的固定电话格式
 * </code>
 */
@Data
public class TelPhone extends AbstractRule {

    protected static final String DEFAULT_MESSAGE_FORMAT = "{0} 不是正确的中国固定电话号码格式";

    /**
     * 验证固定电话格式的正则表达式
     */
    protected static final Pattern CHINESE_TELLPHONE_REGEX = Pattern.compile(
            "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)");

    private String regex = CHINESE_TELLPHONE_REGEX.toString();

    @Override
    public boolean isValid(ValidatorContext context, Object target, ValidatorResult errors) {

        if (target == null) {
            return true;
        }

        if (!CHINESE_TELLPHONE_REGEX.matcher(String.valueOf(target)).matches()) {
            String objectName = context.getCurrentField().getName();
            reject(errors, objectName, DEFAULT_MESSAGE_FORMAT, objectName);
            return false;
        }

        return true;
    }
}
