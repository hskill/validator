package info.ideatower.springboot.validator.core.resource;

import com.google.common.collect.Lists;
import info.ideatower.springboot.validator.core.rule.Rule;
import lombok.Data;

import java.util.List;

/**
 * 验证的单个项目（Item）
 * <p>
 * 在某一个Mark（string）下，有多个验证项目（列表），详见配置文件结果
 * </p>
 */
@Data
public class ValidatorField {


    /**
     * 类型
     * <p>
     * parameter: http get/post parameter
     * header: http header
     * session: javaee session
     * cookie: http cookie
     * path: 以 springmvc 的 pathvariable
     * </p>
     */
    private String type;

    /**
     * 对应需要验证的参数名称
     */
    private String name;

    /**
     * 验证规则集合
     */
    private List<Rule> rules = Lists.newArrayList();

    /**
     * 增加规则
     *
     * @param rule 规则
     */
    public void addRule(Rule rule) {
        this.rules.add(rule);
    }
}
