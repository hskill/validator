package info.ideatower.springboot.validator.core.result;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

/**
 * 单个错误消息
 */
@Data
public class ObjectError implements Serializable {

    private final String objectName;
    private final String message;
    private final String ruleName;

    /**
     * 验证数据
     *
     * @param objectName 数据名称
     * @param ruleName   规则名称
     * @param message    错误消息
     */
    public ObjectError(String objectName, String ruleName, String message) {
        this.objectName = objectName;
        this.ruleName = ruleName;
        this.message = message;
    }

}
