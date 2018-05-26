package info.ideatower.springboot.validator.web;

import com.google.common.collect.Maps;
import info.ideatower.springboot.validator.core.RelatedObject;
import info.ideatower.springboot.validator.core.Validator;
import info.ideatower.springboot.validator.core.ValidatorContext;
import info.ideatower.springboot.validator.core.resource.ValidatorResource;
import info.ideatower.springboot.validator.core.result.ValidatorResult;
import info.ideatower.springboot.validator.web.related.*;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.function.Function;

/**
 * Request 请求校验器
 */
@Slf4j
@Component
public class RequestValidator implements Validator<HttpServletRequest> {

    @Autowired
    private ValidatorResource resource;

    public RequestValidator(ValidatorResource resource) {
        this.resource = resource;
    }

    /**
     * 数据校验
     *
     * @param mark 指定规则集合名称
     * @param request http request
     * @return ValidatorResult
     */
    public ValidatorResult validate(final String mark, final HttpServletRequest request) {
        ValidatorContext context = new ValidatorContext();
        context.setResource(resource);

        val getter = new RelatedObjectGetter(request);
        ValidatorResult errors = new ValidatorResult();

        // 判断是否有该指定的规则集合名称
        if (context.getResource().has(mark)) {
            val validatorFieldList = context.getResource().get(mark);

            //  按参数逐个循环
            validatorFieldList.forEach((field) -> {
                val relatedObject = getter.get(field.getType());
                if (relatedObject != null) {
                    // 设置 context
                    context.setCurrentField(field);
                    context.setRelated(relatedObject);

                    // 拿到需要校验的目标值
                    val targetValue = relatedObject.getValue(field.getName());
                    // 按规则逐个校验
                    field.getRules().forEach((rule) -> {
                        rule.isValid(context, targetValue, errors);
                    });
                } else {
                    log.error("在 {} 中没有找到指定的type: {}", field.getName(), field.getType());
                }
            });
        }
        else {
            log.warn("找不到指定的规则集合：{} ", mark);
        }
        return errors;
    }

    /**
     * 相关对象获取
     */
    private static class RelatedObjectGetter {

        private Map<String, Function<HttpServletRequest, RelatedObject>> pre = Maps.newConcurrentMap();

        private RequestRelatedObject requestRelatedObject;
        private HeaderRelatedObject headerRelatedObject;
        private CookieRelatedObject cookieRelatedObject;
        private SessionRelatedObject sessionRelatedObject;
        private PathVariableRelatedObject pathVariableRelatedObject;

        private final HttpServletRequest request;

        public RelatedObjectGetter(HttpServletRequest request) {
            this.request = request;
            this.pre.put("parameter", this::parameter);
            this.pre.put("header", this::header);
            this.pre.put("cookie", this::cookie);
            this.pre.put("session", this::session);
            this.pre.put("path", this::path);
        }

        public RelatedObject get(String type) {
            if (pre.containsKey(type)) {
                return pre.get(type).apply(this.request);
            }
            return null;
        }


        protected RelatedObject parameter(HttpServletRequest request) {
            if (requestRelatedObject == null) {
                requestRelatedObject = new RequestRelatedObject(request);
            }
            return requestRelatedObject;
        }

        protected RelatedObject header(HttpServletRequest request) {
            if (headerRelatedObject == null) {
                headerRelatedObject = new HeaderRelatedObject(request);
            }
            return headerRelatedObject;
        }

        protected RelatedObject cookie(HttpServletRequest request) {
            if (cookieRelatedObject == null) {
                cookieRelatedObject = new CookieRelatedObject(request);
            }
            return cookieRelatedObject;
        }

        protected RelatedObject session(HttpServletRequest request) {
            if (sessionRelatedObject == null) {
                sessionRelatedObject = new SessionRelatedObject(request);
            }
            return sessionRelatedObject;
        }

        protected RelatedObject path(HttpServletRequest request) {
            if (pathVariableRelatedObject == null) {
                pathVariableRelatedObject = new PathVariableRelatedObject(request);
            }
            return pathVariableRelatedObject;
        }
    }
}
