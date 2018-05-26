package info.ideatower.springboot.validator.core.rule;

import info.ideatower.springboot.validator.core.exception.RuleCannotInstanceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.Map;

/**
 * Rule 构建器
 * 通过相关"数据配置"，构建Rule实例
 * 三种形式的配置
 * Example:
 * <code>
 * # 空值
 * max: ~
 * # 指定值
 * max: 18
 * # 指定值与消息
 * max:
 *   value: 18
 *   message: {target.name}不能超过最大值
 * </code>
 */
@Slf4j
public class RuleFactory {

    /**
     * Rule包名
     */
    public static final String RULE_PACKAGE_NAME = Rule.class.getPackage().getName() + ".extend.";

    /**
     * 通过名称构建校验规则
     *
     * @param ruleName  规则名称。可以传入包名全称
     * @param ruleValue 规则值。可以导入Map或直接量
     * @return
     */
    public Rule getByName(final String ruleName, final Object ruleValue) {

        // 类名称
        // 如果ruleName中含有. 号，则为包名全称
        String className = ruleName.indexOf('.') > 0 ? ruleName : RULE_PACKAGE_NAME + StringUtils.capitalize(ruleName);
        Rule rule = getRuleObject(className);
        try {

            // 1. ruleValue 不能为NULL
            // 2. ruleValue 如果是字符串，则不能为空白字符串
            if (ruleValue != null) {

                if (ruleValue instanceof Map) {
                    BeanUtils.populate(rule, (Map) ruleValue);//从map中拷贝变量至Bean类中
                } else {
                    BeanUtils.setProperty(rule, "value", ruleValue);
                }
            }

            return rule;

        } catch (Exception e) {
            throw new RuleCannotInstanceException(MessageFormat.format("不能向 rule[{0}] 设置指定的值", ruleName), e);
        }
    }

    /**
     * 从类名中，获取实例化的对象
     * @param className
     * @return
     */
    protected Rule getRuleObject(String className) {
        try {
            Rule rule = (Rule) Class.forName(className).newInstance();
            return rule;
        } catch (ClassNotFoundException e) {
            throw new RuleCannotInstanceException(
                    MessageFormat.format("找不到指定的规则类: {0}", className), e);
        } catch (InstantiationException e) {
            throw new RuleCannotInstanceException(
                    MessageFormat.format("指定的规则类不能初始化: {0}", className), e);
        } catch (IllegalAccessException e) {
            throw new RuleCannotInstanceException(
                    MessageFormat.format("指定的规则类不能访问: {0}", className), e);
        }
    }
}
