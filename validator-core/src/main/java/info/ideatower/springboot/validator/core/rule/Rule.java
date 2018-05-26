package info.ideatower.springboot.validator.core.rule;

import info.ideatower.springboot.validator.core.ValidatorContext;
import info.ideatower.springboot.validator.core.result.ValidatorResult;

/**
 * 校验规则
 */
public interface Rule {

    /**
     * 校验
     *
     * @param context 校验上下文
     * @param target  目标数据对象
     * @return
     */
    boolean isValid(ValidatorContext context, Object target, ValidatorResult errors);
}
