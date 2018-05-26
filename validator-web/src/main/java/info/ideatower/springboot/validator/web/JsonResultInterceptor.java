package info.ideatower.springboot.validator.web;

import info.ideatower.springboot.validator.core.result.ValidatorResult;
import info.ideatower.springboot.validator.web.annoation.JsonValidatorResult;
import info.ideatower.springboot.validator.web.json.JsonResultHandler;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * JSON 校验结果返回拦截器
 */
@Slf4j
public class JsonResultInterceptor extends HandlerInterceptorAdapter {

    private final Map<Class, JsonResultHandler> handlerPool;

    private final RequestValidator requestValidator;

    public JsonResultInterceptor(RequestValidator requestValidator) {
        this.requestValidator = requestValidator;
        this.handlerPool = new ConcurrentHashMap<>();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {
            val rule = ((HandlerMethod) handler).getMethodAnnotation(JsonValidatorResult.class);
            if (rule != null && StringUtils.isNotBlank(rule.value())) {
                log.debug("检验参数规则集: {}", rule.value());

                ValidatorResult result = requestValidator.validate(rule.value(), request);
                if (result.hasErrors()) {

                    Class handlerClazz = rule.handler();
                    JsonResultHandler jsonResultHandler = handlerPool.get(handlerClazz);
                    if (jsonResultHandler == null) {
                        jsonResultHandler = rule.handler().newInstance();
                        handlerPool.put(handlerClazz, jsonResultHandler);
                    }
                    jsonResultHandler.handle(response, result);
                    return false;
                }

            }
        }

        return true;
    }
}
