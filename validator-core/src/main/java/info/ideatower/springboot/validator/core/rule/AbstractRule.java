package info.ideatower.springboot.validator.core.rule;


import info.ideatower.springboot.validator.core.result.ObjectError;
import info.ideatower.springboot.validator.core.result.ValidatorResult;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;

/**
 * 抽象类规则
 */
@Data
public abstract class AbstractRule implements Rule {

    protected static final String INVALID_NUMBER_MESSAGE_FORMAT = "{0} 不是正确的值";

    /**
     * 错误提示消息
     */
    protected String message;

    public AbstractRule() {
        this.message = StringUtils.EMPTY;
    }

    /**
     * 产生消息。
     * 默认使用配置文件中的消息，否则使用默认消息
     *
     * @param defaultMessageFormat 默认消息体，注意使用的是 MessageFormat 而不是 Strings.format
     * @param formatArgs           默认消息注入参数
     * @return
     */
    protected String generateMessage(String defaultMessageFormat, Object... formatArgs) {
        if (StringUtils.isNotBlank(this.message)) {
            return this.message;
        }
        return MessageFormat.format(defaultMessageFormat, formatArgs);
    }

    /**
     * 获取简短的类名
     *
     * @return
     */
    public String getName() {
        return StringUtils.uncapitalize(this.getClass().getSimpleName());
    }

    /**
     * 插入错误消息
     *
     * @param objectName 对象名
     * @param errors     错误
     * @param message    错误消息
     */
    protected void reject(ValidatorResult errors, String objectName, String message) {
        errors.reject(new ObjectError(objectName, getName(), message));
    }

    /**
     * 插入错误消息
     *
     * @param objectName    对象名
     * @param errors        错误
     * @param messageFormat 错误消息格式
     * @param messageArgs   错误消息参数
     */
    protected void reject(ValidatorResult errors, String objectName, String messageFormat, Object... messageArgs) {
        reject(errors, objectName, generateMessage(messageFormat, messageArgs));
    }

}
