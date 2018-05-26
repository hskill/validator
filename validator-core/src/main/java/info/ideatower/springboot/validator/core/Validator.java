package info.ideatower.springboot.validator.core;

import info.ideatower.springboot.validator.core.result.ValidatorResult;

/**
 * 验证器
 */
public interface Validator<T> {

    /**
     * 验证
     *
     * @param mark   指定的规则集合名称
     * @param target 目标数据
     * @return ValidatorResult 校验的错误
     */
    ValidatorResult validate(String mark, T target);
}
