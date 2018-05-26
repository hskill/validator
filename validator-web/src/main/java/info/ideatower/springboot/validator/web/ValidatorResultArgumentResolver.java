package info.ideatower.springboot.validator.web;

import info.ideatower.springboot.validator.core.result.ValidatorResult;
import info.ideatower.springboot.validator.web.annoation.ValidatorRule;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * 参数注入器
 */
@Slf4j
public class ValidatorResultArgumentResolver implements HandlerMethodArgumentResolver {

    private RequestValidator requestValidator;

    public ValidatorResultArgumentResolver(RequestValidator requestValidator) {
        this.requestValidator = requestValidator;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(ValidatorResult.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        val rule = parameter.getParameterAnnotation(ValidatorRule.class);
        if (rule != null && StringUtils.isNotBlank(rule.value())) {
            log.debug("检验参数规则集: {}", rule.value());
            return requestValidator.validate(rule.value(), webRequest.getNativeRequest(HttpServletRequest.class));
        }
        else {
            log.warn("参数上找不到指定的 ValidatorRule 注解，或注解没有正确的设置规则集参数: {}", webRequest.getContextPath());
            return new ValidatorResult();
        }

    }
}
