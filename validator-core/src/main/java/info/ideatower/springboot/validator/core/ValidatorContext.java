package info.ideatower.springboot.validator.core;


import info.ideatower.springboot.validator.core.resource.ValidatorField;
import info.ideatower.springboot.validator.core.resource.ValidatorResource;
import lombok.Data;

/**
 * 数据检验上下文
 */
@Data
public class ValidatorContext {

    /**
     * 需要验证的数据对象包装对象
     */
    private RelatedObject related;

    /**
     * 验证的配置资源
     */
    private ValidatorResource resource;

    /**
     * 当前验证的输入域
     */
    private ValidatorField currentField;

}
