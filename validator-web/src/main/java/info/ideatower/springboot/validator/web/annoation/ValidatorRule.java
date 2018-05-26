package info.ideatower.springboot.validator.web.annoation;

import java.lang.annotation.*;

/**
 * 参数注解
 *
 * <code>
 *
 * @GetMapping("/edit/{id}")
 * public String index(User user, @ValidatorRule("user.edit") ValidatorResult result) {
 *
 *     if (result.hasErrors()) {
 *         return JsonResult.error("400", result.getAllErrors());
 *     }
 *
 *     // or do other things
 *     ...
 * }
 * </code>
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidatorRule {

    /**
     * 指定的规则集合名称
     *
     * @return
     */
    String value();
}
