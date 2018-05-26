package info.ideatower.springboot.validator.core.rule.extend;

import info.ideatower.springboot.validator.core.ValidatorContext;
import info.ideatower.springboot.validator.core.result.ValidatorResult;
import info.ideatower.springboot.validator.core.rule.AbstractRule;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * 中国移动手机电话号码
 */
@Data
public class CellPhone extends AbstractRule {

    /**
     * 验证手机号码
     *
     * 移动号码段: 139、138、137、136、135、134、150、151、152、157、158、159、182、183、187、188、147
     * 联通号码段: 130、131、132、136、185、186、145
     * 电信号码段: 133、153、180、189
     */
    protected static final Pattern CHIESE_CELLPHONE_REGEX = Pattern.compile(
            "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$");

    private String regex = CHIESE_CELLPHONE_REGEX.toString();

    @Override
    public boolean isValid(ValidatorContext context, Object target, ValidatorResult errors) {

        if (target == null) {
            return true;
        }

        if (StringUtils.isBlank(String.valueOf(target))) {
            return false;
        }

        if (!CHIESE_CELLPHONE_REGEX.matcher(String.valueOf(target)).matches()) {

            String objectName = context.getCurrentField().getName();
            reject(errors, objectName, "{0} 非中国手机号码格式", target);
            return false;
        }

        return true;
    }
}
