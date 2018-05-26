package info.ideatower.springboot.validator.web.annoation;

import info.ideatower.springboot.validator.web.json.JsonResultHandler;
import info.ideatower.springboot.validator.web.json.SimpleJsonResultHandler;

import java.lang.annotation.*;

/**
 * 方法注解
 *
 * <code>
 *
 * @GetMapping("/edit/{id}")
 * @JsonValidatorResult(value="user.edit")
 * public String index(User user) {
 *     // do other things
 *     ...
 * }
 * </code>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JsonValidatorResult {

    String value();

    Class<? extends JsonResultHandler> handler() default SimpleJsonResultHandler.class;
}
